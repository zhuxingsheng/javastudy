package com.jack.smallcode.callback;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Channel池
 * Created by Jack on 2018/11/15.
 */
public class ChannelPool {

    private Logger logger = LoggerFactory.getLogger(ChannelPool.class);

    private static final int MAX_CONNECTIONS = 2;

    private Channel[] channels;// 使用数组提高性能避免使用锁
    private Object[] locks;// 使用数组提高性能避免使用锁

    private Deque<Callback> jobs;

    String host;
    int port;

    public ChannelPool(String host,int port){
        this.channels = new Channel[MAX_CONNECTIONS];
        this.locks = new Object[MAX_CONNECTIONS];
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            this.locks[i] = new Object();
        }
        this.jobs = new LinkedBlockingDeque<Callback>(1024);

        this.host = host;
        this.port = port;
    }

    private List<Callback> drainJobs() {
        int size = jobs.size();
        logger.info("has {} jobs",size);
        List<Callback> tmpJobs = new ArrayList<Callback>(size);
        Callback tmpCb;
        while((tmpCb = jobs.poll()) != null) {
            tmpJobs.add(tmpCb);
        }
        return tmpJobs;
    }

    /**
     * 异步取得channel
     * @param index
     * @param callback
     */
    public void asynGetChannel(int index,final Callback callback) {
        // 1. 随机获取一条channel
        final int pos = ThreadLocalRandom.current().nextInt(MAX_CONNECTIONS);
        Channel target = channels[pos];

        // 2. 如果获取到了连接，直接返回
        if (target != null && target.isActive()) {
            logger.info("direct success "+index);
            callback.onSuccess(target);
            return;
        }

        synchronized (locks[pos]) {
            target = channels[pos];
            // 2. 如果获取到了连接，直接返回
            if (target != null && target.isActive()) {
                callback.onSuccess(target);
                return;
            }

            // 3.如果连接正在创建中，则加入queue
            if (target instanceof EmptyChannel) {
                boolean result = jobs.offer(callback);
                if (result) {
                    return;
                } else {
                    throw new RuntimeException("Can't connet to target server and the waiting queue is full");
                }
            }

            // 4. 连接尚未创建
            channels[pos] = new EmptyChannel();

           Connector.connect(host, port, new Callback() {
               @Override
               public void onSuccess(Channel channel) {
                   logger.info(index + " ------------connect success---------"+pos + " channel:" +channels[pos].getClass().getName());
                   List<Callback> tmpJobs;//建立一个tempJobs，快速释放锁
                   synchronized (locks[pos]) {
                       // 设置channels，拷贝jobs队列，释放锁
                       channels[pos] = channel;
                       tmpJobs = drainJobs();
                   }
                   for(Callback pendingCallback : tmpJobs) {
                       try {
                           if(pendingCallback != callback) {
                               pendingCallback.onSuccess(channel);
                           }
                       } catch (Exception e) {
                           logger.error("call connectionCallback fail", e);
                       }
                   }
               }

               @Override
               public void onError(Throwable e) {
                   List<Callback> tmpJobs;//建立一个tempJobs，快速释放锁
                   synchronized (locks[pos]) {
                       // 设置channels，拷贝jobs队列，释放锁
                       channels[pos] = null;
                       tmpJobs = drainJobs();
                   }
                   for(Callback pendingCallback : tmpJobs) {
                       try {
                           if(pendingCallback != callback) {
                               pendingCallback.onError(e);
                           }
                       } catch (Exception x) {
                           logger.error("call connectionCallback fail", x);
                       }
                   }
               }
           });


        }
    }

}
