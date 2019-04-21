package com.jack.classloader;

/**
 * 测试类加载及初始化顺序问题
 * Created by jack01.zhu on 2018/9/28.
 */
public class ClassInit {
    private static ClassInit singleton = new ClassInit();
    public static int counter1;
    public static int counter2 = 0;

    private ClassInit() {
        counter1++;
        counter2++;
    }

    public static ClassInit getSingleton() {
        return singleton;
    }
}
