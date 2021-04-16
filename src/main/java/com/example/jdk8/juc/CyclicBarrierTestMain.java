package com.example.jdk8.juc;

import java.util.concurrent.*;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/12/24 15:37
 * @Version 1.0
 */
public class CyclicBarrierTestMain {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4);
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest(barrier);
        for (int i = 0; i < barrier.getParties(); i++) {
            new Thread(cyclicBarrierTest, "player" + i).start();
        }

        System.out.println("main完毕");
    }
}
