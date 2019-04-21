package com.jack.current;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jack01.zhu on 2018/6/16.
 */
public class AtomicBooleanTest1 {
        private static  AtomicBoolean someone = new AtomicBoolean(true);

    private final static ConcurrentMap<String, AtomicBoolean> spinCache = new ConcurrentHashMap<>();

        public static void main(String[] args) {
            AtomicBooleanTest1 st = new AtomicBooleanTest1();
            //返回结果：是否设置成功
            System.out.println(
                    someone.compareAndSet(true,false)
            );

            AtomicBoolean newBoolean = new AtomicBoolean(true);
            AtomicBoolean oldBoolean = spinCache.putIfAbsent("a", newBoolean);
            newBoolean.set(false);
            //newBoolean也是对象，这儿会取到newBoolean新值
            System.out.println(spinCache.get("a").get());
        }
}
