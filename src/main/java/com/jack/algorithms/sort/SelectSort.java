package com.jack.algorithms.sort;

import com.alibaba.fastjson.JSON;

/**
 * 选择排序
 * 在无序区R[1..n]中选出关键字最小的记录R[k]，
 *
 * 将它与无序区的第1个记录R[1]交换，
 *
 * 使R[1..1]和R[2..n]分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区
 *
 * Created by Jack on 2018/11/26.
 */
public class SelectSort extends SortBase {

    public static void main(String[] args) {
        int []array = {2,10,5,22,34,8,12,28,21,11};
        System.out.println(JSON.toJSONString(array));
        selectSort(array);
        System.out.println(JSON.toJSONString(array));
    }

    static void selectSort(int []array) {
        for (int i=0;i<array.length-1;i++) {
            int min  = i;
            for (int j = i+1;j<array.length;j++){
                if(array[min] > array[j]) {
                    min = j;
                }
            }
            if( min != i) {
                int tmp = array[i];
                array[i] = array[min];
                array[min] = tmp;
            }

        }
    }


}
