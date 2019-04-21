package com.jack.juc.tools.semaphore.self;

/**
 * 自定义信号量
 * Created by Jack on 2019/1/30.
 */
public class SelfSemaphore {

    //许可数量
    private int permits = 1;

    public synchronized void P() {
        permits--;
        if(permits < 0 ){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void V(){
        permits++;
        if(permits <=0){
            notifyAll();
        }
    }
}
