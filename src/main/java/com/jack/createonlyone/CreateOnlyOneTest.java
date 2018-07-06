package com.jack.createonlyone;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jack01.zhu on 2018/7/3.
 */
public class CreateOnlyOneTest {
    private final ConcurrentMap<String, InstanceObject> cache = new ConcurrentHashMap<>();

    public InstanceObject get(String key) {
        InstanceObject single = cache.get(key);
        if (single == null) {
            InstanceObject instanceObject = new InstanceObject(key);
            single = cache.putIfAbsent(key, instanceObject);
            if (single == null) {
                single = instanceObject;
            }
        }
        return single;
    }

    private final ConcurrentMap<String, Future<InstanceObject>> casCache = new ConcurrentHashMap<>();

    /**
     * 通过CAS的方式保证只创建一次
     * @param key
     * @return
     */
    public InstanceObject getCas(final String key) {
        Future<InstanceObject> future = casCache.get(key);
        if (future == null) {
            Callable<InstanceObject> callable = new Callable() {
                @Override
                public InstanceObject call() throws Exception {
                    return new InstanceObject(key);
                }
            };
            //FutureTask会被创建多次
            FutureTask<InstanceObject> task = new FutureTask<>(callable);
            System.out.println(Thread.currentThread().getId() + " create task");
            future = casCache.putIfAbsent(key, task);
            if (future == null) {
                future = task;
                task.run();
            }
        }

        try {
            return future.get();
        } catch (Exception e) {
            casCache.remove(key);
            throw new RuntimeException(e);
        }
    }

    private final ConcurrentMap<String, AtomicBoolean> spinCache = new ConcurrentHashMap<>();

    /**
     * 运用atomicboolean自旋
     * @param key
     * @return
     */
    public InstanceObject getAtomic(final String key)  {
        InstanceObject single = cache.get(key);
        if (single == null) {
            System.out.println(Thread.currentThread().getId() + " create task");
            AtomicBoolean newBoolean = new AtomicBoolean(false);
            AtomicBoolean oldBoolean = spinCache.putIfAbsent(key, newBoolean);
            if (oldBoolean == null) {
                cache.put(key, new InstanceObject(key));
                newBoolean.set(true);
            } else {
                //其他线程在自旋状态上自旋，等等被释放
                while (!oldBoolean.get()) {}
            }
            single = cache.get(key);
        }
        return  single;
    }
}
