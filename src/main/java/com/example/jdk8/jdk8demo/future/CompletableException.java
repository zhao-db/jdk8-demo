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
public class CompletableException {

    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 模拟耗时计算，返回结果
            try {
                Thread.sleep(1000); // 等待1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42; // 假设这是计算的结果
        }).thenCompose(value -> CompletableFuture.supplyAsync(() -> {
                    return value / 0;
                })
        ).exceptionally((e -> {
            System.out.println("发生了异常");
            System.out.println("异常信息为：" + e.getMessage());
            return null;
        }));

        // 等待结果并打印
        Integer join = future.join();
        System.out.println(join);
    }
}
