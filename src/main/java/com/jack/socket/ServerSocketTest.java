package com.jack.socket;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket通信服务端
 * Created by Jack on 2018/11/30.
 */
public class ServerSocketTest {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8080);
        while ( true) {
            Socket socket = serverSocket.accept();
            new Thread(new P(socket)).start();
        }
    }
}

class P implements Runnable{

    public P(Socket socket) {

    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId());
    }
}
