package com.jack.algorithms.sort;

import java.util.Arrays;

/**
 * 桶排序
 * http://www.zhuxingsheng.com/blog/algorithmic-slag-sort-bucket-sort.html
 * Created by Jack on 2019/1/5.
 */
public class BucketSort {

    public static void main(String[] args) {
        double []a={0.94,0.83,0.79,0.61,0.55,0.46,0.33,0.25,0.12,0.99,0.102,0.854,0.42345};
        System.err.println(Arrays.toString(a));
        bucketSort(a);
        System.err.println(Arrays.toString(a));
    }

    /**
     * 桶分10个
     * @param array
     */
    private static void bucketSort(double [] array){
        int bucketNum = 10;//10个桶
        double max = Double.MIN_VALUE;
        double min = Double.MIN_VALUE;
        for (double a:array) {
            if(a> max) {
                max = a;
            }
            if(a<min) {
                min = a;
            }
        }
        //区间跨度
        double span  = (max - min) / (bucketNum - 1);
        double [][]buckets = new double[bucketNum][];
        //初始化桶
        for (int b = 0; b<bucketNum;b++) {
            //以排序元素个数初始化每个桶，以防极端情况
            buckets[b] = new double[array.length];
        }
        //每个桶的元素数量
        int [] index = new int[10];
        for (int d = 0;d<array.length;d++) {
            int bucket =  (int)((array[d] - min)/span);
            buckets[bucket][index[bucket]] = array[d];
            index[bucket]++;
        }

        //每个桶排序，直接使用sort函数了
        for(int b = 0; b<10; b++) {
            Arrays.sort(buckets[b]);
        }
        int j = 0;
        for(int b = 0; b<10; b++) {
            if(index[b] == 0) {
                continue;
            }
            //这儿需要特殊处理一下，主要是因为每个桶初始化了array.length，
            // 经过sort排序，比如第一个桶数组变成了[0.0,0.0,......0.002]
            //需要剔掉数组中的0
            for (int bi = array.length-index[b];bi<array.length;bi++) {
                array[j++] = buckets[b][bi];
            }
        }
    }
}
