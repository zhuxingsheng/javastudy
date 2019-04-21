package com.jack.classloader;

import java.lang.reflect.Method;

/**
 * 学习类加载机制
 * Created by Jack on 2018/9/25.
 */
public class ClassLoaderTest {

    public static void main(String[]args) throws Exception {
        ClassLoaderTest test = new ClassLoaderTest();

        System.out.println(test.getClass().getClassLoader());//输出sun.misc.Launcher$AppClassLoader

        System.out.println(test.getClass().getClassLoader().getParent());//输出sun.misc.Launcher$ExtClassLoader

        System.out.println(test.getClass().getClassLoader().getParent().getParent());//输出null

        //=====测试重复加载，类路径中LoaderClass.class存在=================
        //======虽然指定了classloader，但依然输出的是LoaderClass:sun.misc.Launcher$AppClassLoader
        //==删除类路径下的LoaderClass.class,才会输出LoaderClass:com.jack.classloader.MyClassLoader
        //并且loaderclass中创建的对象类加载器也是MyClassLoader
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> loadClass = Class.forName("com.jack.classloader.LoaderClass", true, classLoader);
        Method startMethod = loadClass.getMethod("loader");
        startMethod.invoke(loadClass.newInstance());

        //===当类加载器不一样时，两个class不相等
        MyClassLoader classLoader1 = new MyClassLoader();
        Class<?> loadClass1 = Class.forName("com.jack.classloader.LoaderClass", true, classLoader1);
        System.out.println(loadClass.equals(loadClass1));//输出false
    }
}
