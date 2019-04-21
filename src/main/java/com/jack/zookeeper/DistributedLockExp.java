package com.jack.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * zk版本分布式锁
 *
 * 第一版本，利用节点名称唯一性实现
 *
 * Created by Jack on 2019/4/11.
 */
public class DistributedLockExp extends LockBase{


    private String LOCK_PATH = "/lock";


     public DistributedLockExp(){
            cf = ZkUtil.createCurator();
            try {
                if (cf.checkExists().forPath(LOCK_PATH) == null) {
                    cf.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(LOCK_PATH);
                }
            }catch (Exception e) {
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
        cf.delete().forPath(getLockPath(lockName));
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
        PathChildrenCache pathChildrenCache = new PathChildrenCache(cf, LOCK_PATH, false);
        pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_REMOVED:
                        logger.info("path:" + event.getData().getPath() + " has removed,start to lock:{}",Thread.currentThread().getName());
                        countDownLatch.countDown();
                        break;
                    default:
                        //logger.info(" has changeed,{},start to lock:{}",event.getType(),Thread.currentThread().getName());
                        break;
                }
            }
        });
            Stat stat = cf.checkExists().forPath(getLockPath(lockName));
            //节点存在,此处与unlock非原子操作，如果在checkExists返回true时刻，成功unlock,那此端无限等待
            if (stat == null) {
                logger.info("waitForLock not exists:{}", Thread.currentThread().getName());
                lock(lockName);
            } else {

                logger.info("waitForLock exists:{}", Thread.currentThread().getName());

                countDownLatch.await();
            }

    }

    /**
     * 尝试加锁,直接创建节点，如果节点创建失败，说明加锁失败
     * @param lockName
     * @return
     */
    public boolean tryLock(String lockName) {
        try {
            //创建节点
            String path = cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(getLockPath(lockName), lockName.getBytes());
           logger.info(Thread.currentThread().getName()+ "try lock  success ,path:"+path+" tid:"+Thread.currentThread().getName());
            return true;
        }catch (KeeperException.NodeExistsException e) {
           logger.info("try lock fail,"+" tid:"+Thread.currentThread().getName());
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
