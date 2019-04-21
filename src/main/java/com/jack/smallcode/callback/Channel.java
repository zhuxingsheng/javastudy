package com.jack.smallcode.callback;

/**
 * 模拟socket channel对象
 * Created by Jack on 2018/11/15.
 */
public interface Channel {

    public void sendMessage(String msg);

    boolean isActive();

    void setActive();
}
