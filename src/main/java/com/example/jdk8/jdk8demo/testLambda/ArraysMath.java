package com.example.jdk8.jdk8demo.testLambda;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author Collin
 * @date 2021/7/7 9:40 上午
 */
public class ArraysMath {
    /**
     * 二分查找
     *
     * @param t
     * @return
     */
    public static int ssdf(int t) {
        int[] array = {22, 33, 44, 55, 66, 77, 88};
        int low = 0;
        int high = array.length - 1;
        int mid;
        System.out.println("arr[high]" + array[high]);

        while (low <= high) {
            mid = (low + high) / 2;
            System.out.println("----arr[mid]" + array[mid]);

            if (array[mid] > t) {
                System.out.println("--1");
                high = mid - 1;
                System.out.println("arr[high]" + array[high]);
            } else if (array[mid] < t) {
                System.out.println("+1");
                low = mid + 1;
                System.out.println("arr[low]" + array[low]);
            } else {
                System.out.println("t:" + t + "mid:" + mid + "array[mid]:" + array[mid]);
                return array[mid];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        ssdf(44);
//        int[] array = {22, 33, 44, 55, 66, 77, 88};
//        int i = binarySearch(array, 88);
//        int i = binarySearchRecursive(array, 55, 0, array.length - 1);
//        System.out.println(i);

        List<Integer> sortedList = Lists.newArrayList(1, 3, 5, 7, 9, 11, 13, 15);
        int target = 5;

        int result = binarySearchList(sortedList, target, 0, sortedList.size() - 1);
        System.out.println("目标值 " + target + " 的索引是: " + result);
    }

    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, left, mid - 1);
        } else {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }

    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchList(List<Integer> dataList, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (dataList.get(mid) == target) {
            return mid;
        } else if (dataList.get(mid) > target) {
            return binarySearchList(dataList, target, left, mid - 1);
        } else {
            return binarySearchList(dataList, target, mid + 1, right);
        }
    }

}
