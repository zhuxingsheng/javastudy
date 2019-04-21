package com.jack.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * 测试一下SocketChannel.open的阻塞情况
 * Created by Jack on 2018/12/10.
 */
public class NioClient {

    public static void main(String[] args) {
        connect("10.0.0.2",8080);
    }

    public static void connect(String host,int port){
        try{
            Selector selector = Selector.open();
            System.out.println(" selector open"+new Date());
            InetSocketAddress isa = new InetSocketAddress(host, port);
            //10秒连接超时
            //new Timer().schedule(tt, 10000);
            // 调用open静态方法创建连接到指定主机的SocketChannel
            SocketChannel sc = SocketChannel.open(isa);
            System.out.println(" socketchannel open"+new Date());
            // 设置该sc以非阻塞方式工作
            sc.configureBlocking(false);
            // 将Socketchannel对象注册到指定Selector
            sc.register(selector, SelectionKey.OP_READ);
//            Message msg = new Message();
//            msg.what = 0;
//            msg.obj = sc;
//            handler.sendMessage(msg); // 连接成功
            //new Thread(new NIOReceiveThread(selector, handler)).start();
            System.out.println(" client end ");
        }catch (IOException e)	{
            System.out.println("exception"+new Date());
            e.printStackTrace();
           // handler.sendEmptyMessage(-1); // IO异常
        }
    }
}
