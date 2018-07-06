package com.jack.createonlyone;

import java.util.concurrent.CountDownLatch;

/**
 * 只创建一次 测试主类
 * Created by jack01.zhu on 2018/7/5.
 */
public class CreateOnlyOneMain {

    private static CreateOnlyOneTest test = new CreateOnlyOneTest();

    static boolean useCas = false;

    public static void main(String []args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    if(useCas) {
                      InstanceObject s = test.getCas("One");
                      s.printCount();
                    }else {
                        InstanceObject s = test.getAtomic("One");
                        s.printCount();
                    }
                    countDownLatch.countDown();
                }
            });
            t.start();

        }
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {
                    if(useCas) {
                      InstanceObject s = test.getCas("Two");
                        s.printCount();
                    }else {
                        InstanceObject s = test.getAtomic("Two");
                        s.printCount();
                    }
                    countDownLatch.countDown();
                }
            });
            t.start();

        }
        countDownLatch.await();
    }
}
