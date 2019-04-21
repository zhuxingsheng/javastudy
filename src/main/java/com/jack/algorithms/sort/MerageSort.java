package com.jack.algorithms.sort;

import java.util.Arrays;

/**
 * 归并排序
 * Created by Jack on 2018/12/8.
 */
public class MerageSort extends SortBase{

    public static void main(String[] args) {
        int []array = {2,10,8,22,34,5,12,28,21,11};
//        int []array = {38,27,43,3,9,82,10};
        mergeSort(array,0,array.length-1);
        print(array);
    }

    /**
     * 归并排序
     * @param array
     * @param left
     * @param right
     */
    private static void mergeSort(int []array,int left,int right){
        if(left < right) {
            //分解
            int mid = (left + right) / 2;
            mergeSort(array,left,mid);
            mergeSort(array,mid + 1,right);
            //合并
            merge(array,left,mid,right);
        }
    }

    static void merge(int []array,int left,int mid,int right) {
        System.out.println("merge->left:"+left+" mid:"+mid+" rgiht:"+right);
        int i = left;
        int j = mid +1;
        int tmp[] = new int[right-left+1];//构建tmp数组
        int t = 0;//tmp数组索引
        //填充tmp数组
        for (;i<=mid && j<=right;) {
            if(array[i] < array[j]) {
                tmp[t++] = array[i++];
            } else {
                tmp[t++] = array[j++];
            }
        }
        while (i<=mid) {
            tmp[t++] = array[i++];
        }
        while (j<=right) {
            tmp[t++] = array[j++];
        }

        //再把tmp复制到array
        t = 0;
        while (left<=right) {
            array[left++] = tmp[t++];
        }
        System.out.println("  tmp:"+ Arrays.toString(tmp));
        System.out.println("merge:"+ Arrays.toString(array));
    }




}
