package com.jack.juc.tools.semaphore;

import com.jack.juc.tools.semaphore.self.SelfSemaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * Created by Jack on 2019/1/31.
 */
public class Main {

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
//        testSelf();
        testJUC();

    }

    /**
     * 测试自定义信号量
     */
    private static void testSelf(){
        SelfSemaphore selfSemaphore = new SelfSemaphore();
        Thread consumer = new Thread(new Consumer(selfSemaphore));
        Thread producter = new Thread(new Producter(selfSemaphore));

        consumer.start();
        producter.start();
    }

    private static void testJUC() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    LOGGER.info("start while true");
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LOGGER.info("end while true");
                }
            }
        }).start();

        Thread.sleep(2000);
        semaphore.release();

    }
}
