package com.example.jdk8;

import lombok.SneakyThrows;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/11/27 14:19
 * @Version 1.0
 */
public class Fibonacci {

    public static int F(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        n = F(n - 1) + F(n - 2);
        return n;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println("start");
                    Thread.sleep(10000);
                    System.out.println("end 1123");
                }
            }.start();
        }
    }


}
