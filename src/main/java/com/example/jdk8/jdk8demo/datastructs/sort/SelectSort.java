package com.example.jdk8.jdk8demo.datastructs.sort;

import java.util.Arrays;

/**
 * <p>
 * 选择排序
 * </p>
 *
 * @author zhaodb 2023/3/30
 * @since 3.0.1
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 24, 12, 34, 231, 4, 1, 21312, 423, 42, 13, 41};
        selectSort(arr);
        System.out.println("排序后结果" + Arrays.toString(arr));

    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int minValue = array[i];
            //加1的目的是假设当前I 的值就为最小 与后面比较
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < minValue) {
                    minValue = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = minValue;
            }
        }
    }
}
