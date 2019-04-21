package com.jack.classloader;

import java.util.UUID;

/**
 * 主动 被动使用问题测试
 * Created by Jack on 2018/9/28.
 */
public class ClassInitTest3 {
    public static void main(String[] args) {
        String x = F.s;
    }
}

class F {
    //因为UUID.randomUUID().toString()这个方法，是运行期确认的，所以，这不是被动使用
    static final String s = UUID.randomUUID().toString();

    static {
        System.out.println("Initialize class F");
    }
}
