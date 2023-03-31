package com.example.jdk8.jdk8demo.datastructs.search;

/**
 * <p>
 * 二分查找
 * </p>
 *
 * @author zhaodb 2023/3/31
 * @since 3.0.1
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
//        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };
        int index = BinarySearch(arr, 0, arr.length - 1, 1234);
        System.out.println("index = " + index);
    }

    public static int BinarySearch(int[] arr, int left, int right, int search) {
        if (left > right) {
            return -1;
        }
        int midIndex = (left + right) / 2;
        int midValue = arr[midIndex];
        if (search == midValue) {
            return midIndex;
        }
        if (search > midValue) {
            return BinarySearch(arr, midIndex + 1, right, search);
        }
        if (search < midValue) {
            return BinarySearch(arr, left, midIndex - 1, search);
        }
        return -1;
    }

}
