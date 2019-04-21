package com.jack.juc.tools.semaphore;

import com.jack.juc.tools.semaphore.self.SelfSemaphore;

/**
 * Created by Jack on 2019/1/31.
 */
public class Producter implements Runnable {
    private SelfSemaphore selfSemaphore;

    public Producter(SelfSemaphore selfSemaphore){
        this.selfSemaphore = selfSemaphore;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++) {
            selfSemaphore.P();
            System.err.println("--Producter:"+i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            selfSemaphore.V();
        }
    }
}
