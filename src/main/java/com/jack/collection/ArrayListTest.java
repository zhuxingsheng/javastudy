package com.jack.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: studyjava
 * @description   ArrayList中的ensureCapacity()方法的使用
 * @author: zhuxingsheng@gmail.com
 * @created: 2019-12-09 15:50
 */
public class ArrayListTest {

    public static void main(String[] args) {
        final int N = 1000000;
        Object obj = new Object();

        //没用调用ensureCapacity()方法初始化ArrayList对象
        ArrayList list = new ArrayList();
        long startTime = System.currentTimeMillis();
        for(int i=0;i<=N;i++){
            list.add(obj);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("没有调用ensureCapacity()方法所用时间：" + (endTime - startTime) + "ms");

        //调用ensureCapacity()方法初始化ArrayList对象
        list = new ArrayList();
        startTime = System.currentTimeMillis();
        list.ensureCapacity(N);//预先设置list的大小,这个方法其实类似于初始化时指定容量大小
        for(int i=0;i<=N;i++){
            list.add(obj);
        }
        endTime = System.currentTimeMillis();
        System.out.println("调用ensureCapacity()方法所用时间：" + (endTime - startTime) + "ms");
    }
}
