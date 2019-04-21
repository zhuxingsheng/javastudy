package com.jack.zookeeper;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 思路二：利用临时有序节点
 * Created by Jack on 2019/4/16.
 */
public class DistributedLock1 extends LockBase{

    private String PARENT_LOCK_PATH = "/lock";

    private String lockName;

    private String beforeNode;

    private String currentNode;

    public DistributedLock1(String lockName){
        super();
        this.lockName = lockName;
        try {
            if (cf.checkExists().forPath(PARENT_LOCK_PATH) == null) {
                cf.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(PARENT_LOCK_PATH);
            }
        }catch (Exception e) {
        }
    }

    private String getLockPath(String lockName){
        return PARENT_LOCK_PATH + "/" + lockName;
    }

    @Override
    public boolean tryLock(String lockname) {
        try {
            currentNode = cf.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(getLockPath(lockname));
            logger.info("try lock,{},path:{}",Thread.currentThread().getName(),currentNode);
            boolean isMinNode = isMinNode();
            return isMinNode;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private   List<String> getOrderChildren() throws Exception {
        List<String> strings = cf.getChildren().forPath(PARENT_LOCK_PATH);
        Collections.sort(strings);
        strings = Lists.transform(strings, new Function<String, String>() {
            @Override
            public String apply(@Nullable String input) {
                return PARENT_LOCK_PATH + "/" + input;
            }
        });
        return strings;
    }

    /**
     * 是不是最小节点
     * @return
     * @throws Exception
     */
    private boolean isMinNode() throws Exception {
        List<String> strings = getOrderChildren();
        logger.info("min node:{},path:{},{}",strings.get(0),currentNode,Thread.currentThread().getName());
        int index = strings.indexOf(currentNode);
        if(0 == index) {
            logger.info("try lock success,{},{}", currentNode, Thread.currentThread().getName());
            return true;
        } else {
            beforeNode = strings.get(index-1);
            logger.info("try lock fail,current:{},before:{},{}",currentNode,beforeNode,Thread.currentThread().getName());
            return false;
        }
    }

    CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void waitForLock(String lockName) throws Exception {
        logger.info("waitForLock {}:{}",beforeNode, Thread.currentThread().getName());
        boolean hasLock = false;
        while(!hasLock) {
            //是不是最小节点
            boolean isMin = isMinNode();
            if (isMin) {//是 则成功获取锁
                logger.info("waitForLock getLock:{}", Thread.currentThread().getName());
                break;
            } else {
                try {
                    cf.getData().usingWatcher(new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            switch (event.getType()) {
                                case NodeDeleted:
                                    logger.info("path:" + event.getPath() + " has removed,before:{},start to lock:{}", beforeNode, Thread.currentThread().getName());
                                    countDownLatch.countDown();
                                    break;
                            }
                        }
                    }).forPath(beforeNode);
                    logger.info("waitForLock waiting:{}", Thread.currentThread().getName());
                    countDownLatch.await();
                    logger.info("开始抢占:{},{}", Thread.currentThread().getName(), currentNode);
                }catch (KeeperException.NoNodeException e){
                    logger.info("waitForLock has delete:{},{}",beforeNode, Thread.currentThread().getName());
                }
            }
        }
    }

    @Override
    public boolean unlock(String lockName) throws Exception {
        logger.info("start unlock:" + currentNode + " tid:" + Thread.currentThread().getName());
        cf.delete().guaranteed().forPath(currentNode);
        logger.info("end unlock:" + currentNode + " tid:" + Thread.currentThread().getName());
        return true;
    }

}
