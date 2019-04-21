package com.jack.redisson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jack on 2019/1/23.
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger("LockTest");
    public static void main(String[] args) throws InterruptedException {
        logger.info("start lock Main");
        DistributedRedisLock.lock("lockname");
        logger.info("has lock Main");
        logger.info("unlock Main");
        DistributedRedisLock.unlock("lockname");
    }
}
