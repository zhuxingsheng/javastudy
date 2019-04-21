package com.jack.thrift;

import org.apache.thrift.TException;

/**
 * Created by jack01.zhu on 2018/8/29.
 */
public class HelloServiceImpl implements HelloService.Iface {

    public String helloString(String what) throws TException {
        return "result:"+what;
    }
}
