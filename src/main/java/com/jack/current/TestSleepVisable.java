package com.jack.current;

public class TestSleepVisable {
    boolean b = true;

    public static void main(String[] args) throws InterruptedException {
        TestSleepVisable t = new TestSleepVisable();
        t.test();

    }

    private void test(){
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始");
            while (b) {
//                 下面这个sleep会解决可见性问题
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }, "A");
        t1.setPriority(10);
        t1.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始");
            b = false;
            System.out.println(Thread.currentThread().getName() + "结束");
        }, "B");
        t2.setPriority(1);
        t2.start();
    }
}
