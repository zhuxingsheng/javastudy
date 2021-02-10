package com.jack.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

/**
 * @description: test inheritable thread local
 * @author: zhuxingsheng@gmail.com
 * @create: 2021-02-10 11:58
 */
public class InheritableThreadLocalTest {

    @Test
    public  void testThreadpool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        final ThreadLocal<String>  threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("parent");
        for(int i=0;i<1;i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() +" get parent value:" + threadLocal.get());
                    threadLocal.set("sun");
                    System.out.println(Thread.currentThread().getId() + "==" + threadLocal.get());
                }
            };
            executorService.execute(runnable);
            Thread.sleep(100);
            executorService.execute(runnable);
            Thread.sleep(100);
            System.out.println("main:" + threadLocal.get());
        }
        executorService.shutdown();
    }

}
