package com.jack.threadlocal;

/**
 * Created by jack01.zhu on 2018/4/13.
 */
public class TestTreadlocal {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
        public static void main(String[]rags) {
            threadLocal.set("test");
            System.out.println();
        }
}
