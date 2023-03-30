package com.example.jdk8.jdk8demo.datastructs.sort;

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
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        long start = System.currentTimeMillis();
        //测试冒泡排序
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序后的时间是=" + (end - start) / 1000 + "s");
    }


    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int tmp;
            boolean flag = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}
