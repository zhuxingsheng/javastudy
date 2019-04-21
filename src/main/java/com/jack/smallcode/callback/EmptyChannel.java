package com.jack.smallcode.callback;

/**
 * 空channel
 * Created by Jack on 2018/11/15.
 */
public class EmptyChannel implements Channel {
    @Override
    public void sendMessage(String msg) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setActive() {

    }
}
