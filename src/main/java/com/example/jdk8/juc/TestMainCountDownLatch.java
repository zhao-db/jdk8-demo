package com.example.jdk8.juc;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/12/24 15:22
 * @Version 1.0
 */
public class TestMainCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(4);
        TestCountDownLatch downLatch = new TestCountDownLatch(latch);
        MyThread myThread = new MyThread(latch);
        MyThread myThread1 = new MyThread(latch);
        MyThread myThread2 = new MyThread(latch);
        MyThread myThread3 = new MyThread(latch);
        ExecutorService service = new ThreadPoolExecutor(4, 4, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        service.submit(myThread);
        service.submit(myThread1);
        service.submit(myThread2);
        service.submit(myThread3);
      /*  for (int i = 0; i < 4; i++) {
            new Thread(downLatch, "player" + i).start();
        }*/
        System.out.println("等待所有线程完毕");
        latch.await();
        System.out.println("开始");
    }

    public static class MyThread implements Runnable {
        private CountDownLatch downLatch;

        public MyThread(CountDownLatch downLatch) {
            this.downLatch = downLatch;
        }

        @SneakyThrows
        @Override
        public void run() {
            Random random = new Random();
            int rand = random.nextInt(3000);
            Thread.sleep(rand);
            System.out.println(Thread.currentThread().getName() + "已经准备好了，所使用时间" + (double) rand / 1000 + "s");
            downLatch.countDown();
        }
    }


}
