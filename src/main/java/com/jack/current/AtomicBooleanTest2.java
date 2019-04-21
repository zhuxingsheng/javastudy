package com.jack.current;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jack01.zhu on 2018/6/16.
 */
public class AtomicBooleanTest2 {
    public static void main(String[] args) {

        ThreadDemo1 td = new ThreadDemo1();
        new Thread(td).start();

        while(true){
            if(td.getFlag()){
                System.out.println("主线程flag:" + td.getFlag());
                break;
            }
        }
    }
}

class ThreadDemo1 implements Runnable{
    //共享变量
    private  AtomicBoolean  flag = new AtomicBoolean(false);

    public boolean getFlag() {
        return flag.get();
    }

    public void setFlag(boolean flag) {
        this.flag.set(flag);
    }

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }

        setFlag(true);

        System.out.println("其他线程flag=" + getFlag());
    }
}

