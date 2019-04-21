package com.jack.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;

/**
 * socket客户端测试
 * Created by Jack on 2018/11/29.
 */
public class SocketTest {

    public static void main(String[] args) throws Exception{

        //testConnectionTimeout();

        System.out.println("testConnectionTimeout end");

        System.out.println(new Date());
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost",8080),20000);
        //设置 socket timeout
        socket.setSoTimeout(3000);
        System.out.println("Connected.");
        InputStream in = socket.getInputStream();
        System.out.println("reading...");
        try {
            in.read();
        }catch (Exception e) {
            System.out.println(new Date() + " exception");
            e.printStackTrace();
        }
        System.out.println("read end");
    }

    private static void testConnectionTimeout() throws IOException {
        System.out.println(new Date());
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("10.0.0.1", 8080), 2000);
        }finally {
            System.out.println(new Date());
        }
    }
}
