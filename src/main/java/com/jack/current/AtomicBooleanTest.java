package com.jack.current;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jack01.zhu on 2018/6/16.
 */
public class AtomicBooleanTest implements Runnable {
        private boolean bValue = false;
        private AtomicBoolean someone = new AtomicBoolean();

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()
                    + "  start:  " + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + "  end:  " + System.currentTimeMillis());
//            if (someone.compareAndSet(false, true)) {
            if (bValue == false) {
                    System.out.println(Thread.currentThread().getName()
                            + ": after judgment,the someone is " + someone);
                    try {
                        Thread.sleep(1 * 1000);
                        bValue = true;
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()
                            + " i am coming");
                    System.out.println(Thread.currentThread().getName()
                            + " i have to leaving");
                    someone.set(false);
                    //break;
                }
                else {
                    System.out.println(Thread.currentThread().getName()
                            + " i can't visit the house");
                }
            }

        public static void main(String[] args) {
            AtomicBooleanTest st = new AtomicBooleanTest();
            new Thread(st, "thread_2").start();
            new Thread(st, "thread_1").start();
        }
}
