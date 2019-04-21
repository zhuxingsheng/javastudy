package com.jack.slidingwindow;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jack01.zhu on 2018/6/8.
 */
public class GuavaRateLimiter {

    public static void main(String[]arg) {

        testSmoothWarmingUp();

    }


    public static void testSmoothBursty(){
        RateLimiter rateLimiter = RateLimiter.create(0.5);

        List<Runnable> tasks = new ArrayList<Runnable>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new Request(i));
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        for(Runnable task:tasks) {
            System.out.println("等待时间：" + rateLimiter.acquire());
            executorService.execute(task);
        }

    }

    public static void testSmoothWarmingUp(){
        //每秒5个，1500ms后达到正常速率
        RateLimiter rateLimiter = RateLimiter.create(5,1500, TimeUnit.MILLISECONDS);

        List<Runnable> tasks = new ArrayList<Runnable>();
        for (int i = 0; i < 10; i++) {
            tasks.add(new Request(i));
        }

        ExecutorService executorService = Executors.newCachedThreadPool();
        for(Runnable task:tasks) {
            System.out.println("等待时间：" + rateLimiter.acquire());
            executorService.execute(task);
        }
        executorService.shutdown();

    }



    private LoadingCache<Long, AtomicLong> counter =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(2, TimeUnit.SECONDS)
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long seconds) throws Exception {
                            return new AtomicLong(0);
                        }
                    });

    public static long permit = 50;

    public boolean getData() throws ExecutionException {

        //得到当前秒
        long currentSeconds = System.currentTimeMillis() / 1000;
        if(counter.get(currentSeconds).incrementAndGet() > permit) {
            return false;
        }
        //业务处理
        return true;

    }




}

class Request implements  Runnable{

    int no;
    public Request(int no){
        this.no = no;
    }

    @Override
    public void run() {
        System.out.println(no +" handle request "+System.currentTimeMillis());
    }
}
