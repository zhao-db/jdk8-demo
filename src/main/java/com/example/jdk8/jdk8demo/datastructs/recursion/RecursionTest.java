package com.example.jdk8.jdk8demo.datastructs.recursion;

import static com.alibaba.fastjson.JSONPatch.OperationType.test;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/3/29
 * @since 3.0.1
 */
public class RecursionTest {

    public static void test(int n) {
        if (n > 1) {
            test(n - 1);
        }
        System.out.println(n);
    }

    public static void main(String[] args) {
        int factorial = factorial(4);
        System.out.println("factorial = " + factorial);
        System.out.println("hah");
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }

}
