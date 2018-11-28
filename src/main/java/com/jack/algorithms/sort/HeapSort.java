package com.jack.algorithms.sort;

import com.google.common.primitives.Ints;

import java.util.Arrays;

/**
 * a.将无需序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;

 　b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;

 　c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 *
 * Created by Jack on 2018/11/27.
 */
public class HeapSort extends SortBase{

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
     * 这儿写得利于理解一点，把最后一步改掉，循环从数组中把第一位取出来放到新数组，剩余的再去构造堆
     * @param array
     */
    static void heapSort(int []array) {
        //原始数组
        int [] oriental = Arrays.copyOf(array,array.length);
        //最终排序好的数组
        int [] result = new int[array.length];

        int length = array.length;
        for ( int i = 0;i< length;i++) {
            //1.构造堆
            buildHeap(array);
            //构建完array[0]就是最大的元素,取出来放到排序好的数组
            result[i] = array[0];
            //[0]取走之后，一个新数组，再去构造堆
            array = Arrays.copyOfRange(array, 1,length-i);
        }
        System.out.println("finish:"+ Arrays.toString(result));
    }

    /**
     * 构造椎
     * @param array
     */
    static void buildHeap(int []array) {
        //(n/2-1)~0的节点才有子节点
        for (int i= array.length/2 -1 ;i>=0;i--) {
//            System.out.println("i:"+i);
//            System.out.println("start:"+ Arrays.toString(array));
            int k = i;
            for (int left = k * 2 + 1; left < array.length; left = k * 2 + 1) {
               // System.out.println("build:"+ Arrays.toString(array));
                int max = left;
                int right = left + 1;
                //有右节点
                if (right < array.length) {
                    if (array[right] > array[left]) {
                        max = right;
                    }
                }
                //max是left子节点,那就交换左子节点与k
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
        }
        //System.out.println("  end:"+ Arrays.toString(array));
    }


}
