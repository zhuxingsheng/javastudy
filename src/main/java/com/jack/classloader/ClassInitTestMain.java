package com.jack.classloader;

/**
 * 通过输出结果，推测类加载过程
 * Created by jack01.zhu on 2018/9/28.
 */
public class ClassInitTestMain {

    public static void main(String[] args) {
        ClassInit classInitTest = ClassInit.getSingleton();
        System.out.println("counter1=" + classInitTest.counter1);
        System.out.println("counter2=" + classInitTest.counter2);
    }
}
