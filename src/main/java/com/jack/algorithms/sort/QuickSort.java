package com.jack.algorithms.sort;

import com.alibaba.fastjson.JSON;

/**
 * 快速排序
 *
 * 左右指针法
 *1. 先从数列中取出一个数作为基准数。
 2. 分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
 3. 再对左右区间重复第二步，直到各区间只有一个数
 *
 * Created by Jack on 2018/11/5.
 */
public class QuickSort extends SortBase{

    public static void main(String[] args) {
        //int []array = {2,10,8,22,34,5,12,28,21,11};
        int []array = {22,12,28,21,34};
        System.out.println(JSON.toJSONString(array));
        quicSort(array,0,array.length-1);
        System.out.println(JSON.toJSONString(array));
    }

    /**
     * 分治法,从小到大排序
     * @param array
     */
    public static int partition(int []array,int start,int end) {

        //取一位做为基数
        int pivot = array[end];//这儿取最后一位作为基数了

        int left = start;
        int right = end-1;//从倒数第二位开始比较

        //从两头向中间搜索,从小到大排序
        while (left < right) {
            //从left端开始搜索
            while(left < right && array[left] <= pivot) {
                left ++;
            }
            //从right端开始搜索
            while (left < right && array[right] >= pivot) {
                right -- ;
            }
            //两数交换,大的放到右边，小的放到左边
            if(left < right) {
                swap(array,left,right);
                left++;
                right--;
            }
        }
        //
        if(left != end && array[left] > array[end] ) {
            swap(array,left,end);
            return left;
        }


        //right == left
        return left+1;
    }

    /**
     * 分治法,从小到大排序
     * @param array
     * @param start
     * @param end
     */
    private static void quicSort(int []array,int start,int end){
        if( start < end ){
            int partition = partition(array,start,end);
            print(array);
            quicSort(array,start,partition-1);
            quicSort(array,partition+1,end);
        }
    }


}
