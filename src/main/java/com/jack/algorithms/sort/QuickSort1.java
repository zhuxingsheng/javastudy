package com.jack.algorithms.sort;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序，挖坑法
 *
 *
 * Created by Jack on 2018/11/9.
 */
public class QuickSort1 extends SortBase {

    public static void main(String[] args) {
        QuickSort1 qs = new QuickSort1();
        //int []array = {2,10,8,22,34,5,12,28,21,11};
        int []array = {22,12,28,21,34};
        System.out.println(JSON.toJSONString(array));
        qs.QuickSort(array,0,array.length-1);
        System.out.println(JSON.toJSONString(array));
    }


    int partion(int arr[], int begin, int end)
    {
        int pit = arr[end];//挖最右数，留一个坑
        int left = begin;
        int right = end;

        while (left < right)
        {
            //从最左开始搜索比坑大的数
            while (left < right && arr[left] <= pit)
                left++;
            if (left<right) {
                arr[right] = arr[left];//拿left去填坑，left成为新坑
            }
            while (left < right && arr[right] >= pit)
                right--;
            if (left < right) {
                arr[left] = arr[right];//right去填left坑，left成为新坑
            }
        }

        arr[left] = pit;//用key填坑
        return left;
    }

    void QuickSort(int arr[], int left, int right)
    {
        if (left < right)
        {
            int mid = partion(arr, left, right);
            print(arr);
            QuickSort(arr, left, mid - 1);
            QuickSort(arr, mid + 1, right);
        }
    }
}
