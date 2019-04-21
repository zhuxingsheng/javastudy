package com.jack.redisson;

import org.redisson.Redisson;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RSemaphore;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jack on 2019/1/29.
 */
public class SemaphoreTest {

    //从配置类中获取redisson对象
    private static Redisson redisson = RedissonManager.getRedisson();


    public static void main(String[] args) throws InterruptedException {
//        RSemaphore semaphore = redisson.getSemaphore("semaphore");
//        semaphore.tryAcquire(1);
//
//        RSemaphore semaphore1 = redisson.getSemaphore("semaphore");
//        semaphore1.tryAcquire(2,TimeUnit.SECONDS);
        System.err.println("start");
        RPermitExpirableSemaphore permitExpirableSemaphore = redisson.getPermitExpirableSemaphore("mySemaphore1");
//        permitExpirableSemaphore.acquire();
        System.err.println("start1");
//// 获取一个信号，有效期只有2秒钟。
        String permitId = permitExpirableSemaphore.acquire(10, TimeUnit.SECONDS);
        System.err.println("pp" + permitId);
        Thread.sleep(11000);

        System.err.println("sleep end");

        permitId = permitExpirableSemaphore.acquire(2, TimeUnit.SECONDS);
        System.err.println("pp" + permitId);
//// ...
        permitExpirableSemaphore.release(permitId);
        System.err.println("end");
    }
}
