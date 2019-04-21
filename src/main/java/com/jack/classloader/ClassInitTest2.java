package com.jack.classloader;

/**
 * <clinit> 与 <init> 区别
 * Created by jack01.zhu on 2018/9/28.
 */
public class ClassInitTest2 {
    static {
        System.out.println("cinit");

        i = 3;//可以赋值
        //System.out.println(i);//但不能使用，语法错误
    }

    private static int i = 1;

    {
        System.out.println("init");//实例化构造器，
    }

    public static void main(String [] args)	{
        new ClassInitTest2();
        new ClassInitTest2();
        String str = "str";
        System.out.println(str);
    }
}


