package com.jack.classloader;

/**
 * static 与 static final 对初始化的区别
 * Created by jack01.zhu on 2018/9/28.
 */
public class ClassInitFinalTest {
    public static  int age = 20;

    static {
        //如果age定义为static final，这儿就不会执行
        System.out.println("静态初始化！");
    }

    public static void main(String args[]){
        System.out.println(ClassInitFinalTest.age);
    }
}
