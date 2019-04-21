package com.jack.smallcode.callback;

/**
 * 回调
 * Created by Jack on 2018/11/15.
 */
public interface Callback {

    public void onSuccess(Channel channel);

    public void onError(Throwable e);
}
