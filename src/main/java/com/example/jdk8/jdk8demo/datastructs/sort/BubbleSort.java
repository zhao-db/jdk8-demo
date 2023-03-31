package com.example.jdk8.jdk8demo.datastructs.sort;

import java.util.Arrays;

/**
 * <p>
 * 冒泡排序
 * </p>
 *
 * @author zhaodb 2023/3/30
 * @since 3.0.1
 */
public class BubbleSort {

    public static void main(String[] args) {
        //测试一下冒泡排序的速度O(n^2), 给80000个数据，测试
        //创建要给80000个的随机的数组
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int) (Math.random() * 8000000); //生成一个[0, 8000000) 数
//        }
        int[] arr = {5, 3, 24, 12, 34, 231, 4, 1, 21312, 423, 42, 13, 41};

        long start = System.currentTimeMillis();
        //测试冒泡排序
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序后的时间是=" + (end - start) / 1000 + "s");
        System.out.println("排序后结果" + Arrays.toString(arr));

    }


    public static void bubbleSort(int[] array) {
        int tmp = 0;
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            //减i的目的是 第一次已经将最大的放到最后位置了 后续轮次不需要在和倒数第一位比较
            //第二轮同理 所以减i
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    flag = true;
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = true;
            }
        }
    }
}
