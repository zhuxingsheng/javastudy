package com.jack.smallcode.callback;

/**
 * 异步回调
 * Created by Jack on 2018/11/16.
 */
public class Main {

    public static void main(String[] args) {
        ChannelPool pool = new ChannelPool("127.0.0.1",5656);
        for(int i=0;i<20;i++) {
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(j+ " start get");
                    pool.asynGetChannel(j,new Callback() {
                        @Override
                        public void onSuccess(Channel channel) {
                            System.out.println(j + " onSuccess " + System.currentTimeMillis());
                            channel.sendMessage("hello");
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println(e.getMessage());
                        }
                    });
                    System.out.println(j + " end "+System.currentTimeMillis());

                }
            }).start();
            if( i > 5) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
