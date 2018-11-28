package com.jack.algorithms.sort;

import java.util.Arrays;

/**
 * a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;

 　b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;

 　c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 *
 * Created by Jack on 2018/11/27.
 */
public class HeapSort2 extends SortBase{

    public static void main(String[] args) {
        int []array = {2,10,8,22,34,5,12,28,21,11};
        print(array);
        heapSort(array);
        print(array);
    }

    /**
     *
     * a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;

     　b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;

     　c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。

     * 整个排序过程，就是构造堆的过程
     *
     * @param array
     */
    static void heapSort(int []array) {
        for ( int i = 0;i<array.length;i++) {
            //1.构造堆
            buildHeap(array,array.length-i);
            //构建完array[0]就是最大的元素
            //将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
            swap(array, 0, array.length - i - 1);
        }
    }

    static void buildHeap(int []array,int length) {
        //(n/2-1)~0的节点才有子节点
        for (int i= length/2 -1 ;i>=0;i--) {
            System.out.println("i:"+i);
            System.out.println("start:"+ Arrays.toString(array));
            int k = i;
            for (int left = k * 2 + 1; left < length; left = k * 2 + 1) {
                System.out.println("build:"+ Arrays.toString(array));
                int max = left;
                int right = left + 1;
                //有右节点
                if (right < length) {
                    if (array[right] > array[left]) {
                        max = right;
                    }
                }
                //max是left,那就交换左节点与i
                if (array[max] > array[k]) {
                    swap(array, max, k);
                    // 下面就是非常关键的一步了
                    // 如果子节点更换了，那么，以子节点为根的子树会不会受到影响呢？
                    // 所以，循环对子节点所在的树继续进行判断
                    k = left;
                } else {
                    break;
                }
            }
            System.out.println("  end:"+ Arrays.toString(array));
        }
    }


}
