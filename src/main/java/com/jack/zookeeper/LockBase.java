package com.jack.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jack on 2019/4/18.
 */
public abstract class LockBase {

    protected static Logger logger = LoggerFactory.getLogger(LockBase.class);

    private String LOCK_PATH = "/lock";

    protected CuratorFramework cf;


    public LockBase(){
        cf = ZkUtil.createCurator();
    }

    public void lock(String lockName) throws Exception {

        if(tryLock(lockName)) {
            logger.info("lock success,lockName:{},tid:{}",lockName,Thread.currentThread().getName());
        } else {
            //尝试加锁失败，需要阻塞,等待
            logger.info("waitforlock start,lockname:{},threadId:{}",lockName,Thread.currentThread().getName());
            waitForLock(lockName);
            logger.info("waitforlock end,lockname:{},threadId:{}",lockName,Thread.currentThread().getName());
//            lock(lockName);
        }
    }

    public  abstract boolean tryLock(String lockname);

    /**
     * 尝试加锁失败后，阻塞等待
     * @param lockName
     * @throws Exception
     */
    public abstract void waitForLock(String lockName) throws Exception;

    public abstract boolean unlock(String lockName) throws Exception;
}
