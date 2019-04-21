package com.jack.juc.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jack on 2019/1/24.
 */
public class CountDownLatchTest {

    private static Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);

    public static void main(String[] args) throws InterruptedException {

        testIntrupt();
    }

    /**
     * 在await的过程中，进行countDown()，看看await的返回值是什么
     */
    public static void testIntrupt(){

        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info(""+Thread.currentThread().getName()+" start");
                try {
                    boolean r = latch.await(5,TimeUnit.SECONDS);
                    logger.info(""+Thread.currentThread().getName()+" result "+r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info(""+Thread.currentThread().getName()+" start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info(""+Thread.currentThread().getName()+" start 1");
                latch.countDown();
                logger.info(""+Thread.currentThread().getName()+" end");
            }
        },"B").start();



    }

    public static void test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        long timeoutMillis = 3000;
        //latch.countDown();
        //测试一下返回值的false/true的意义
        //如果计数到达零，则返回 true；如果在计数到达零之前超过了等待时间，则返回 false
        boolean await = latch.await(timeoutMillis, TimeUnit.MILLISECONDS);
        logger.info(""+await);
    }
}
