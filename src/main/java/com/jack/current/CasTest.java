package com.jack.current;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 通过cas进行安全 ++ 操作计数
 * Created by jack01.zhu on 2018/6/16.
 */
public class CasTest {

    private int i=0;

    private AtomicBoolean lock = new AtomicBoolean(false);

    public static void main(String[]arg) throws InterruptedException {
        for ( int t=0;t<3;t++) {
            CountDownLatch countDownLatch = new CountDownLatch(20);
            CasTest test = new CasTest();
            test.i=0;
            test.test(countDownLatch,true);
            countDownLatch.await();
//            if(test.i!=100*20) {
//                System.out.println(test.i);
//            }
            System.out.println(test.i);
        }
    }

    private void count() {
        i++;
    }

    private void safeCount(){
        while (true) {
//            if(!lock.get()) {
//                System.out.println(Thread.currentThread().getId() +"  " + lock.get());
//            }
            if(lock.compareAndSet(false,true)) {
                //System.out.println(Thread.currentThread().getId() +"  " + lock.get());
                i++;
                lock.set(false);
                break;
            }
        }
    }

    public void test(CountDownLatch countDownLatch,boolean safe ){
        List<Thread> threads = Lists.newArrayList();
        for ( int t = 0;t<countDownLatch.getCount();t++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int ts = 0;ts < 100;ts++) {
                        if(safe) {
                            safeCount();
                        }else {
                            count();
                        }
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            threads.add(thread);
        }
        for(Thread thread:threads) {
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }
}
