package com.jack.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jack01.zhu on 2018/8/30.
 */
public class NettyClient implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    @Override
    public void run() {
        EventLoopGroup group = new NioEventLoopGroup(1);
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_TIMEOUT, 2000);
            b.channel(NioSocketChannel.class);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                    pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                    pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

                    pipeline.addLast("handler", new ClientHandler());
                }
            });
            logger.info("start to connect");
            ChannelFuture f = b.connect("127.0.0.1", 5656).sync();
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        logger.info("connect success");
                    } else {
                        logger.error("connect fail");
                    }
                }
            });
            f.channel().writeAndFlush("Netty Hello Service!" + Thread.currentThread().getName() + ":--->:" + Thread.currentThread().getId());

            logger.info("再次连接");
            f = b.connect("127.0.0.1", 5656).awaitUninterruptibly();


            f.channel().closeFuture().sync();


        } catch (Exception e) {
            logger.error("connect error ", e);
        } finally {
            group.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 1; i++) {
            new Thread(new NettyClient(), "【this thread】 " + i).start();
        }
    }

}