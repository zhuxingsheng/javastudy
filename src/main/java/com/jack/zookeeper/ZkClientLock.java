package com.jack.zookeeper;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Jack on 2019/4/19.
 */
public class ZkClientLock {

    private static final Logger logger = LoggerFactory.getLogger(ZkClientLock.class);


    private String LOCK_PATH = "/lock";

    private ZkClient zkClient;

    public ZkClientLock() {
        zkClient = ZkUtil.createZkClient();
        try {
            if (!zkClient.exists(LOCK_PATH)) {
                try {
                    zkClient.createPersistent(LOCK_PATH);
                }catch (ZkNodeExistsException e) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lock(String lockName) throws Exception {

        if(tryLock(lockName)) {
            logger.info("lock success,lockName:{},tid:{}",lockName,Thread.currentThread().getName());
        } else {
            //尝试加锁失败，需要阻塞,等待
            logger.info("waitforlock start,lockname:{},threadId:{}",lockName,Thread.currentThread().getName());
            waitForLock(lockName);
            logger.info("waitforlock end,lockname:{},threadId:{}",lockName,Thread.currentThread().getName());
            lock(lockName);
        }
    }




    public boolean unlock(String lockName) throws Exception {
        logger.info("start unlock:" + lockName + " tid:" + Thread.currentThread().getName());
        zkClient.delete(getLockPath(lockName));
        logger.info("end unlock:" + lockName + " tid:" + Thread.currentThread().getName());
        return true;
    }

    private String getLockPath(String lockName) {
        return LOCK_PATH + "/" + lockName;
    }
    CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 尝试加锁失败后，阻塞等待
     * @param lockName
     * @throws Exception
     */
    public void waitForLock(String lockName) throws Exception {
        //监听子节点

        zkClient.subscribeChildChanges(getLockPath(lockName), new IZkChildListener() {

            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
               logger.info("==={},{},{}",parentPath, JSON.toJSONString(currentChilds),Thread.currentThread().getName());
               if(currentChilds == null) {
                   countDownLatch.countDown();
               }
            }
        });

        if (!zkClient.exists(getLockPath(lockName))) {
            logger.info("not need wait ,path not exist,{}",Thread.currentThread().getName());
            return;
        }



        /**
         Stat stat = cf.checkExists().forPath(getLockPath(lockName));
         //节点存在,此处与unlock非原子操作，如果在checkExists返回true时刻，成功unlock,那此端无限等待
         if (stat != null) {
         logger.info("waitForLock exists:{}", Thread.currentThread().getName());
         countDownLatch = new CountDownLatch(1);
         countDownLatch.await();
         } else {
         logger.info("waitForLock not exists:{}", Thread.currentThread().getName());
         lock(lockName);
         }
         */
        countDownLatch.await();

    }

    /**
     * 尝试加锁,直接创建节点，如果节点创建失败，说明加锁失败
     * @param lockName
     * @return
     */
    public boolean tryLock(String lockName) {
        logger.info(Thread.currentThread().getName() + " start try lock ");
        try {
            //创建节点
            String path = getLockPath(lockName);
            zkClient.createPersistent(path, lockName.getBytes());
            logger.info(Thread.currentThread().getName() + "try lock  success ,path:" + path + " tid:" + Thread.currentThread().getName());
            return true;
        }catch (ZkNodeExistsException e) {
            return false;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
