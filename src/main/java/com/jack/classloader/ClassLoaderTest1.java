package com.jack.classloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 类加载时间性能测试
 *
 * 看一下类加载需要消耗的时间
 * Created by Jack on 2018/10/8.
 */
public class ClassLoaderTest1 {
    public static void main(String[] args) throws SQLException {
        long s = System.nanoTime();

        LoaderClass loaderClass = new LoaderClass();
        long e = System.nanoTime();
        //第一次时间
        System.out.println(e - s);
        e = System.nanoTime();
        //第二次实例，但已经加载过，不再需要加载
        LoaderClass loaderClass1 = new LoaderClass();
        long e1 = System.nanoTime();
        //第二次时间
        System.out.println(e1 - e);
    }
}
