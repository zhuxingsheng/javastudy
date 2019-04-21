package com.jack.algorithms.sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * http://www.zhuxingsheng.com/blog/algorithmic-slag-sorting-cardinal-sorting.html
 *
 * Created by Jack on 2019/1/6.
 */
public class RadixSort {

    public static void main(String[] args) {
        int a[] = {53, 3, 542, 748, 14, 214, 154, 63, 616};
        radixSort(a);

    }

    /**
     * 基数排序
     * @param array
     */
    private static void radixSort(int []array){
        //取最大值，好计算位数
        int max = Integer.MIN_VALUE;
        for (int a:array) {
            if(a> max) {
                max = a;
            }
        }
        //(0~9)10个桶
        int [][]buckets = new int[10][];

        //初始化桶
        for(int b=0;b<10;b++) {
            buckets[b] = new int[array.length];
        }

        //每个桶的元素数量
        int [] index = new int[10];
        //按每一位数排序
        for (int radix = 1;max/radix>0;radix*=10){
            //把元素放到各自的桶内
            for (int a:array) {
                //得到每位数
                int per = a/radix%10;
                buckets[per][index[per]] = a;
                index[per]++;
            }
            //各个桶的数据依次放回数组
            int j = 0;
            for (int b=0;b<10;b++) {
                //去掉桶中别的元素
                for (int i = 0;i<index[b];i++){
                    array[j++] = buckets[b][i];
                }
            }
            System.err.println("按第"+radix+"位,排序：" + Arrays.toString(array));
            //清空计数器
            index = new int[10];
        }
    }
}
