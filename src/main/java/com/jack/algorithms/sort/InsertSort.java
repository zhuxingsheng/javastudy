package com.jack.algorithms.sort;

import com.alibaba.fastjson.JSON;

/**
 *
 * 插入排序
 *
 * 插入排序的算法思路很简单，联想打扑克牌时理顺手中牌的时候，你从左往右依次检查你的牌，并将其和前面的牌进行比较，然后将其插入正确的位置
 *
 *
 * Created by Jack on 2018/11/19.
 */
public class InsertSort extends SortBase{

    public static void main(String[] args) {
        int []array = {22,12,28,21,34};
        System.out.println(JSON.toJSONString(array));
        insertSort1(array);
        System.out.println(JSON.toJSONString(array));
    }

    static void insertSort(int []sorts) {
        for(int i = 1;i<sorts.length;i++) {
            int tmp = sorts[i];
            int j= i-1;
            for (;j>=0;j--) {
                if(tmp < sorts[j] ) {//比tmp大的全部往右移动
                    sorts[j+1] = sorts[j];
                }else {
                    break;
                }
            }
            System.out.println(j);
            sorts[++j] = tmp;
        }
    }

    /**
     * 上面的for循环太难看了，改成while
     * @param sorts
     */
    static void insertSort1(int []sorts) {
        for(int i = 1;i<sorts.length;i++) {
            int tmp = sorts[i];//哨兵
            int j= i-1;
            while(j >=0 && tmp < sorts[j]) {
                sorts[j+1] = sorts[j];//比tmp大的全部往右移动
                j--;
            }
            //别的移完位置，把哨兵放到正确的位置
            sorts[++j] = tmp;
        }
    }
}
