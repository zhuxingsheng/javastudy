package com.jack.threadlocal;

/**
 * ThreadLoal学习
 * Created by jack01.zhu on 2018/4/13.
 */
public class TestTreadlocal {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static void main(String[]rags) {
        threadLocal = new InheritableThreadLocal<>();

        threadLocal.set("test");
        System.out.println("get from threadlocal:"+threadLocal.get());
        Thread thread = new Thread(){
            @Override
            public void run(){
                System.out.println("MyThread = " + threadLocal.get());
                threadLocal.set("mythread-value");
                System.out.println("get from MyThread again = " + threadLocal.get());
            }
        };
        thread.start();
        System.out.println("get from threadlocal:"+threadLocal.get());
    }
}
