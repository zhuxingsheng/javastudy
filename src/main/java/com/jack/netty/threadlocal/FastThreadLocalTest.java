package com.jack.netty.threadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 测试FastThreadLocal与threadlocal的性能
 * Created by Jack on 2018/10/18.
 */
public class FastThreadLocalTest {


    public static void main(String[] args) {
        testFast(100);
        testSlow(100);
    }

    private static void testFast(int threadLocalCount) {
        final FastThreadLocal<String>[] caches = new FastThreadLocal[threadLocalCount];
        final Thread mainThread = Thread.currentThread();
        for (int i = 0; i < threadLocalCount; i++) {
            caches[i] = new FastThreadLocal();
        }
        Thread t = new FastThreadLocalThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < threadLocalCount; i++) {
                    caches[i].set("fast");
                }
                long start = System.nanoTime();
                for (int i = 0; i < threadLocalCount; i++) {
                    for (int j = 0; j < 1000000; j++) {
                        caches[i].get();
                    }
                }
                long end = System.nanoTime();
                System.out.println("fast[" + TimeUnit.NANOSECONDS.toMillis(end - start) +
                        "]ms");
                LockSupport.unpark(mainThread);
            }

        });
        t.start();
        LockSupport.park(mainThread);
    }

    private static void testSlow(int threadLocalCount) {
        final ThreadLocal<String>[] caches = new ThreadLocal[threadLocalCount];
        final Thread mainThread = Thread.currentThread();
        for (int i=0;i<threadLocalCount;i++) {
            caches[i] = new ThreadLocal();
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<threadLocalCount;i++) {
                    caches[i].set("slow");
                }
                long start = System.nanoTime();
                for (int i=0;i<threadLocalCount;i++) {
                    for (int j=0;j<1000000;j++) {
                        caches[i].get();
                    }
                }
                long end = System.nanoTime();
                System.out.println("slow[" + TimeUnit.NANOSECONDS.toMillis(end - start) +
                        "]ms");
                LockSupport.unpark(mainThread);
            }

        });
        t.start();
        LockSupport.park(mainThread);
    }
}
