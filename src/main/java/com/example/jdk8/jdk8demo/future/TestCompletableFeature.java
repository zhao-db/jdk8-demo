package com.example.jdk8.jdk8demo.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * CompletableFuture.runAsync(Runnable runnable): 异用于异步地执行一个 Runnable 任务，
 * 并且不返回任何结果（返回类型为 CompletableFuture<Void>）。这在你只关心任务的执行而不关心其返回值时非常有用。
 * </p>
 *
 * @author zhaodb 2024/6/30
 * @since 3.0.1
 */
public class TestCompletableFeature {

    private static ExecutorService executors = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 使用默认的 ForkJoinPool 异步执行任务
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // 模拟一个耗时的任务
            try {
                Thread.sleep(1000); // 假设任务需要2秒来完成
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 恢复中断状态
                throw new RuntimeException(e); // 抛出运行时异常以便可以看到异常信息
            }
            System.out.println("任务执行完毕！");
        });

        // 在主线程中继续执行其他操作，不需要等待上面的任务完成
        System.out.println("主线程继续执行...");

        // 如果你想等待任务完成，可以调用 future.join() 或 future.get()，但请注意这可能会阻塞当前线程
        // 这里我们只是打印出任务是否已经完成
        System.out.println("任务是否完成: " + future.isDone());

        // 注意：由于任务是异步执行的，所以上面的 isDone() 方法可能返回 false，因为任务可能还没有完成

        // 你可以通过 future.thenRun(...) 来添加在任务完成后要执行的代码
        future.thenRun(() -> System.out.println("任务完成后执行的代码"));

        // 注意：thenRun 中的代码也是异步执行的，并且可能在主线程之后执行

        // 为了确保主线程在异步任务完成后才结束，可以调用 future.join()
        try {
            future.join(); // 等待异步任务完成
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 现在可以确定异步任务已经完成
        System.out.println("主线程结束");
    }
}

