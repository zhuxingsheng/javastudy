package com.jack.zookeeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Jack on 2019/4/16.
 */
public class LockTest {

    private final static Logger logger = LoggerFactory.getLogger(LockTest.class);

    private static int n = 0;

    int m = 0;

    private void testM() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(10);
        for (int i=0;i<10;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    m++;

                    latch.countDown();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    m++;

                }
            }).start();
        }
        latch.await();
        System.out.println("end ,m="+m);
    }


    public static void main(String[] args) throws Exception {
        LockTest lockTest = new LockTest();
        lockTest.testM();

        lockTest.testN();


    }

    private void testN() throws InterruptedException {
        int num = 3;
        CountDownLatch latch = new CountDownLatch(num);
        //开启多个线程去加锁
        for (int i=0;i<num;i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DistributedLockExp lock = new DistributedLockExp();
//                    DistributedLockExp lock = new DistributedLockExp();
//                    ZkClientLock lock = new ZkClientLock();
                    try {
                        logger.info("lock start,tid:{}",Thread.currentThread().getName());
                        lock.lock("lock1");
                        n++;
                        Thread.sleep(5);
                        n++;
                        lock.unlock("lock1");

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
