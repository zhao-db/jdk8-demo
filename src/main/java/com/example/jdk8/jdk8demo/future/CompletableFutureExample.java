package com.example.jdk8.jdk8demo.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture.supplyAsync(Supplier<U> supplier):
 * 异步执行给定的 Supplier 函数，并返回一个新的 CompletableFuture，当函数完成时，该 CompletableFuture 将以函数的结果完成。
 */
public class CompletableFutureExample {  
  
    public static void main(String[] args) throws ExecutionException, InterruptedException {  
        // 使用 supplyAsync 异步执行一个计算  
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {  
            // 模拟一个耗时的计算  
            try {  
                Thread.sleep(2000); // 等待 2 秒  
            } catch (InterruptedException e) {  
                Thread.currentThread().interrupt();  
                throw new IllegalStateException(e);  
            }  
            return "Hello, CompletableFuture!";  
        });  
  
        // 主线程可以继续执行其他任务，而不需要等待上面的计算完成  
  
        // 当需要结果时，可以调用 get() 方法（这会阻塞，直到结果可用）  
        String result = future.get(); // 这里会等待上面的计算完成，然后返回结果  
        System.out.println(result); // 输出 "Hello, CompletableFuture!"  
    }  
}