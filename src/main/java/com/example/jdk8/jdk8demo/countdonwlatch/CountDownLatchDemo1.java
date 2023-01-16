package com.example.jdk8.jdk8demo.countdonwlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 流水线5个人见擦汗产品质量 全检查完才算完成  1等多
 * </p>
 *
 * @author zhaodb 2023/1/16
 * @since 3.0.1
 */
@Slf4j
public class CountDownLatchDemo1 {


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        log.info("No.{}完成了检查", no);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            };
            service.submit(runnable);
        }
        log.info("等待5个人检查完....");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("检查完成");
    }


}
