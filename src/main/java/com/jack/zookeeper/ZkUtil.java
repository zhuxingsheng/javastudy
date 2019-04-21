package com.jack.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by Jack on 2019/4/16.
 */
public class ZkUtil {

    private static String CONNECT_ADDR = "127.0.0.1";

    /** session超时时间 */
    static final int SESSION_OUTTIME = 5000;//ms



    public static final CuratorFramework createCurator(){
        //1 重试策略：初试时间为1s 重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        //2 通过工厂创建连接
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                //.sessionTimeoutMs(SESSION_OUTTIME)
                .retryPolicy(retryPolicy)
                .build();
        //3 开启连接
        cf.start();
        return cf;
    }

    public static final ZkClient createZkClient(){
        ZkClient zkc = new ZkClient(new ZkConnection(CONNECT_ADDR), SESSION_OUTTIME);
        zkc.setZkSerializer(new SerializableSerializer());
        return zkc;
    }
}
