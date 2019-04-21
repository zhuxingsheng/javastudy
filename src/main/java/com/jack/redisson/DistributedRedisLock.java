package com.jack.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jack on 2019/1/18.
 */
public class DistributedRedisLock {
    //从配置类中获取redisson对象
    private static Redisson redisson = RedissonManager.getRedisson();
    private static final String LOCK_TITLE = "redisLock_";
    //加锁
    public static boolean lock(String lockName){
        //声明key对象
        String key = LOCK_TITLE + lockName;
        //获取锁对象
        RLock mylock = redisson.getLock(key);
        //阻塞型
        mylock.lock();
        System.err.println("======lock======"+Thread.currentThread().getName());
        //加锁成功
        return  true;
    }
    //加锁
    public static boolean tryLock(String lockName){
        //声明key对象
        String key = LOCK_TITLE + lockName;
        //获取锁对象
        RLock mylock = redisson.getLock(key);
        //阻塞型
        mylock.tryLock();
        System.err.println("======lock======"+Thread.currentThread().getName());
        //加锁成功
        return  true;
    }
    //锁的释放
    public static void unlock(String lockName){
        //必须是和加锁时的同一个key
        String key = LOCK_TITLE + lockName;
        //获取所对象
        RLock mylock = redisson.getLock(key);
        //释放锁（解锁）
        mylock.unlock();
        System.err.println("======unlock======"+Thread.currentThread().getName());
    }
}
