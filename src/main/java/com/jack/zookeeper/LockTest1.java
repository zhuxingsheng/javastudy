package com.jack.zookeeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Jack on 2019/4/16.
 */
public class LockTest1 {

    private final static Logger logger = LoggerFactory.getLogger(LockTest1.class);

    private static int n = 0;

    int m = 0;



    public static void main(String[] args) throws Exception {
        LockTest1 lockTest = new LockTest1();

        lockTest.testN();


    }

    private void testN() throws InterruptedException {
        int num = 10;
        CountDownLatch latch = new CountDownLatch(num);
        //开启多个线程去加锁
        for (int i=0;i<num;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DistributedLock1 lock = new DistributedLock1("jjk");
                    try {
                        logger.info("lock start,tid:{}",Thread.currentThread().getName());
                        lock.lock("lock1");
                        n++;
                        Thread.sleep(4);
                        n++;
                        lock.unlock("lock1");



//                        lock.lock("lock1");
//
//                        lock.waitForLock("lock1");
//
//                        lock.unlock("lock1");


                        logger.info("lock end,tid:{}",Thread.currentThread().getName());
                        Thread.sleep(300);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }
            },"thread-"+i).start();
        }
        latch.await();
        System.out.println("end ,n="+n);
    }


}
