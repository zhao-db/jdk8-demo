package com.example.jdk8.jdk8demo.testLambda;

/**
 * @author Collin
 * @date 2021/7/7 9:40 上午
 */
public class ArraysMath {

    public static int ssdf() {
        int[] array = {22, 33, 44, 55, 66, 77, 88};
        int low = 0;
        int high = array.length - 1;
        int mid;
        int t = 44;
        System.out.println("arr[high]"+array[high]);

        while (low <= high) {
            mid = (low + high) / 2;
            System.out.println("----arr[mid]"+array[mid]);

            if (array[mid] > t) {
                System.out.println("--1");
                high = mid - 1;
                System.out.println("arr[high]"+array[high]);
            } else if (array[mid] < t) {
                System.out.println("+1");
                low = mid + 1;
                System.out.println("arr[low]"+array[low]);
            } else {
                System.out.println("t:"+t+"mid:"+mid +"array[mid]:"+array[mid]);
                return array[mid];
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        ssdf();

    }

}
