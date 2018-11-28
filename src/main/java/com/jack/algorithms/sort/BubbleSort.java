package com.jack.algorithms.sort;

import com.alibaba.fastjson.JSON;

/**
 * 冒泡排序
 *
 * http://www.zhuxingsheng.com/blog/algorithmic-slag-sort-bubble.html
 *
 * Created by Jack on 2018/10/30.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int [] arrays = {5,6,3,1,8,7,2,4};
        bubbleSort(arrays);
        System.out.println(JSON.toJSONString(arrays));
        int []arrays1 = {5,6,3,1,8,7,2,4};
        System.out.println(JSON.toJSONString(arrays1));
        bubbleSort2(arrays1);
        System.out.println(JSON.toJSONString(arrays1));


    }

    /**
     * 传统写法
     * @param sorts
     */
    private static void bubbleSort(int [] sorts){
        for (int i=0;i<sorts.length-1;i++) {
            for (int j= 0;j<sorts.length-1-i;j++) {
                if(sorts[j] < sorts[j+1]) {
                    swap(sorts,j,j+1);
                }
            }
        }
    }

    /**
     * 如果部分已经排序,设置一个flag,有序就跳出循环
     * @param sorts
     */
    private static void bubbleSort1(int [] sorts){
        for (int i=0;i<sorts.length-1;i++) {
            boolean flag = false;
            for (int j= 0;j<sorts.length-1-i;j++) {
                if(sorts[j] < sorts[j+1]) {
                    swap(sorts,j,j+1);
                    flag = true;
                }
            }
            if(!flag) {
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序，定向冒泡排序
     * @param sorts
     */
    private static void bubbleSort2(int [] sorts){
        int i = 0;
        int j = sorts.length-1;
        while (i <= j) {
            for(int s = i;s<j;s++) {//从左往右
                if(sorts[s] < sorts[s+1]) {
                    swap(sorts, s, s + 1);
                }
            }

            System.out.println(JSON.toJSONString(sorts));
            for(int e = j;e>i;e--) {//从右往左
                if(sorts[e] > sorts[e-1]) {
                    swap(sorts, e, e - 1);
                }
            }
            j--;i++;
            System.out.println(JSON.toJSONString(sorts));
        }

    }

    private static void swap(int []sorts,int i,int j){
        int tmp = sorts[i];
        sorts[i] = sorts[j];
        sorts[j] = tmp;
    }



}
