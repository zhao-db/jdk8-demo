package com.example.jdk8.jdk8demo.countdonwlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 百米跑 5等1裁判 同时起跑 ,所有人到终点后 比赛结束   多等1&1等多
 * </p>
 *
 * @author zhaodb 2023/1/16
 * @since 3.0.1
 */
@Slf4j
public class CountDownLatch1And2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    log.info("No.{},准备完毕等待发令枪", no);
                    try {
                        begin.await();
                        log.info("No.{},起跑了", no);
                        Thread.sleep((long) (Math.random() * 10000));
                        log.info("No.{}跑到终点", no);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            };
            service.submit(runnable);
        }
        Thread.sleep(5000);
        log.info("发令枪响 比赛开始！");
        begin.countDown();
        //等待五个人到达终点
        end.await();
        log.info("所有人到达终点,比赛结束");
    }

}
