package com.jack.current;

/**
 * Created by jack01.zhu on 2018/7/3.
 */
public class SynchronizedTest {

    public synchronized void test1(){

    }

    public void test2(){
        synchronized (this){

        }
    }
}
