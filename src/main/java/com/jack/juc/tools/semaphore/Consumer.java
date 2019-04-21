package com.jack.juc.tools.semaphore;

import com.jack.juc.tools.semaphore.self.SelfSemaphore;

/**
 * Created by Jack on 2019/1/31.
 */
public class Consumer implements Runnable {

    private SelfSemaphore selfSemaphore;

    public Consumer(SelfSemaphore selfSemaphore){
        this.selfSemaphore = selfSemaphore;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++) {
            selfSemaphore.P();
            System.err.println("--consumer:"+i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            selfSemaphore.V();
        }
    }
}
