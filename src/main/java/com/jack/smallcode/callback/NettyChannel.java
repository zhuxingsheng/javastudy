package com.jack.smallcode.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Jack on 2018/11/15.
 */
public class NettyChannel implements Channel{

    private boolean isActive;

    private io.netty.channel.Channel channel;

    private Logger logger = LoggerFactory.getLogger(NettyChannel.class);

   public NettyChannel(io.netty.channel.Channel channel) {
       this.channel = channel;
   }

    @Override
    public void sendMessage(String msg) {
        System.out.println("send message");
        channel.writeAndFlush(msg);
    }

    @Override
    public boolean isActive() {
        return channel.isActive();
    }

    @Override
    public void setActive() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isActive = true;
    }
}
