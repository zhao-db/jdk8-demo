package com.example.jdk8.jdk8demo.future;

import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2024/6/30
 * @since 3.0.1
 */
public class TestCompletableCombine {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时计算，返回结果
            try {
                Thread.sleep(1000); // 等待1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42; // 假设这是第一个任务的结果
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时计算，返回结果
            try {
                Thread.sleep(1500); // 等待0.5秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 13; // 假设这是第二个任务的结果
        });

// 使用 thenCombine 合并两个任务的结果
        CompletableFuture<Integer> resultFuture = future1.thenCombine(future2, (a, b) -> a + b);


// 等待结果并打印
        Integer join = resultFuture.join();
        long end = System.currentTimeMillis();
        double cost = (end - start) / 1000;
        System.out.println("costTime:" + cost + ", result: " + join);//55
    }
}
