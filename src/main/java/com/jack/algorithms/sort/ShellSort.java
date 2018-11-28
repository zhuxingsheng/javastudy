package com.jack.algorithms.sort;

/**
 * shell 排序
 * Created by Jack on 2018/11/21.
 */
public class ShellSort extends SortBase{

    public static void main(String[] args) {
        int []array = {2,10,8,22,34,5,12,28,21,11};
        shellSort(array);
        print(array);
    }

    static void shellSort(int []array) {
        //以length/2为增量
        for (int gap=array.length/2;gap>0;gap= gap/2) {
            //以gap分组，进行排序
           for(int i = gap;i<array.length;i++){
               int tmp = array[i];
               int j = i - gap;
               //相对直接插入，步长从1变成了gap
               while ( j>=0 && tmp<array[j] ) {
                   array[j+gap] = array[j];
                   j=j-gap;
               }
               array[j+gap] = tmp;
           }
        }
    }
}
