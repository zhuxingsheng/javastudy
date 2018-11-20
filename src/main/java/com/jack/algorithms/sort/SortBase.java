package com.jack.algorithms.sort;

import com.alibaba.fastjson.JSON;

/**
 * Created by Jack on 2018/11/8.
 */
public class SortBase {

    public static void print(int []array){
        System.out.println(JSON.toJSONString(array));
    }

    public static void swap(int []sorts,int i,int j){
        int tmp = sorts[i];
        sorts[i] = sorts[j];
        sorts[j] = tmp;
    }
}
