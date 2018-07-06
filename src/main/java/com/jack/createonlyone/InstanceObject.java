package com.jack.createonlyone;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jack01.zhu on 2018/7/4.
 */
public class InstanceObject {

    private static final AtomicInteger count = new AtomicInteger(0);

    private String key;

    public InstanceObject(String key) {
        this.key = key;
        System.out.println(Thread.currentThread().getId() + " " +Thread.currentThread().getName() +" create "+key  );
    }

    public void printCount(){
        System.out.println("Count "  + count.incrementAndGet() + " for key: " + key );
    }



}
