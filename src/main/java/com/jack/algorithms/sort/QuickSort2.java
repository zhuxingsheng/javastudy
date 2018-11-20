package com.jack.algorithms.sort;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序，前后指针法
 * Created by Jack on 2018/11/9.
 */
public class QuickSort2 extends SortBase {

    public static void main(String[] args) {
        QuickSort2 qs = new QuickSort2();
        //int []array = {2,10,8,22,34,5,12,28,21,11};
        int []array = {22,12,28,21,34};
        System.out.println(JSON.toJSONString(array));
        qs.QuickSort(array,0,array.length-1);
        System.out.println(JSON.toJSONString(array));
    }

    int partion(int arr[], int left, int right)
    {
        int key = arr[right];//取最后一位为key

        int cur = left;//当前指针
        int prev = left - 1;//前一个指针

        while (cur < right)
        {
            if(arr[cur] < key && ++prev != cur){//发现比key小的数
                swap(arr,cur,prev);
            }
            cur++;
        }
        //最后++prev交换
        swap(arr,++prev,cur);
        return prev;
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
