package com.example.jdk8.jdk8demo.datastructs.sort;

import java.util.Arrays;

/**
 * <p>
 * 插入排序
 * </p>
 *
 * @author zhaodb 2023/3/30
 * @since 3.0.1
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 24, 12, 34, 231, 4, 1, 21312, 423, 42, 13, 41};
        insertSort(arr);
        System.out.println("arr = " + Arrays.toString(arr));
    }

    public static void insertSort(int[] array) {
        int insertVal = 0;
        int insertIndex = 0;
        //从第一个数开始比较 默认最前方位有序
        for (int i = 1; i < array.length; i++) {
            //插入当前比较节点的前一节点
            insertIndex = i - 1;
            insertVal = array[i];
            //比较索引大于0 并且 插入值和前一个值比较 小于前面一值 则进行插入
            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            //说明不是本次轮询的值 变化过 需要把旧值插入
            if (insertIndex + 1 != i) {
                array[insertIndex + 1] = insertVal;
            }
        }
    }
}
