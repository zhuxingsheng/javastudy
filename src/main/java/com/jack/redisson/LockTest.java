package com.jack.redisson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jack on 2019/1/23.
 */
public class LockTest {
    private static Logger logger = LoggerFactory.getLogger("LockTest");
    public static void main(String[] args) throws InterruptedException {
        //Thread.sleep(6000);
        logger.info("start lock");
        DistributedRedisLock.lock("lockname");
        logger.info("sleep 10s");
        Thread.sleep(200*1000);
        logger.info("unlock");
        DistributedRedisLock.unlock("lockname");

    }
}
