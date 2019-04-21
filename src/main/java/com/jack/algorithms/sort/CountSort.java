package com.jack.algorithms.sort;

import java.util.Arrays;

/**
 * 计数排序
 * Created by Jack on 2019/2/18.
 */
public class CountSort {

    public static void main(String[] args) {
        int a[] = {61,61,66, 63, 64, 64,66};
        countSort1(a);
    }

    /**
     * 计数排序,不稳定
     * @param array
     */
    public static void countSort(int []array){
        //最大最小数
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for ( int a:array) {
            if(min > a) {
                min = a;
            }
            if(max < a) {
                max = a;
            }
        }

        //数组索引数量,统计数组计数
        // max-min 主要是考虑到像[90,99,93,95]不是从很小数开始的数组排序，减小空间消耗
        int indexCount = max - min + 1;
        System.err.println("统计数组长度"+indexCount);
        int []countArray = new int[indexCount];

        for (int i=0;i<array.length;i++){
            countArray[array[i]-min]++;
        }
        System.err.println("countArray:"+Arrays.toString(countArray));

        //排好序的数组
        int [] sortArray = new int[array.length];
        int index = 0;
        for (int i=0;i<indexCount;i++) {
            for (int j=0;j<countArray[i];j++){
                sortArray[index++] = min + i;
            }
        }
        //输出就是有序
        System.err.println(Arrays.toString(sortArray));
    }

    /**
     * 稳定计数排序
     * @param array
     */
    public static void countSort1(int []array) {
        //最大最小数
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for ( int a:array) {
            if(min > a) {
                min = a;
            }
            if(max < a) {
                max = a;
            }
        }

        //数组索引数量,统计数组计数
        // max-min 主要是考虑到像[90,99,93,95]不是从很小数开始的数组排序，减小空间消耗
        int indexCount = max - min + 1;
        System.err.println("统计数组长度"+indexCount);
        int []countArray = new int[indexCount];

        for (int i=0;i<array.length;i++){
            System.err.println(array[i]-min);
            countArray[array[i]-min]++;
        }
        System.err.println("countArray:"+Arrays.toString(countArray));
        //位置数组
        int []pointArray = new int[indexCount];

        int sum =0;
        for (int i = 0;i<indexCount;i++){
            sum += countArray[i];
            pointArray[i] = sum;
        }
        System.err.println("pointArray:"+Arrays.toString(pointArray));
        System.err.println("aaaaaArray:"+Arrays.toString(array));
        //排好序的数组
        int [] sortArray = new int[array.length];
        for (int i=array.length-1;i>=0;i--) {
                sortArray[pointArray[array[i]-min] -1 ] = array[i];
                pointArray[array[i]-min]--;
        }
        //输出就是有序
        System.err.println(Arrays.toString(sortArray));
    }


}
