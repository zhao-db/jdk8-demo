package com.example.jdk8.jdk8demo.schedule;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.alibaba.excel.util.StringUtils;

/**
 * Spring动态周期定时任务<br>
 * 在不停应用的情况下更改任务执行周期
 *
 * @Author zhaodb
 */
@Lazy(false)
@Component
@EnableScheduling
@Slf4j
public class SpringDynamicCronTask implements SchedulingConfigurer {

    private static final String DEFAULT_CRON = "* * 10 * * ?";
    private static String CRON = "* * 10 * * ?";
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("UPDATE_CRON_EXPRESS", false));


    public SpringDynamicCronTask() {
        executor.scheduleAtFixedRate(() -> {
            Random random = new Random();
            int nextInt = random.nextInt(9);
            CRON = "0/" + nextInt + " * * * * ?";
            log.info("cron change to: " + CRON);
        }, 0, 30, TimeUnit.SECONDS);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> {
            doJobMethod();
        }, (triggerContext) -> {
            // 任务触发，可修改任务的执行周期
            //2.2 合法性校验.
            if (StringUtils.isBlank(CRON)) {
                // Omitted Code ..
                log.info("trackScheduler定时器的cron参数为空！！！！！");
                //如果为空则赋默认值
                CRON = DEFAULT_CRON;
            }
            //2.3 返回执行周期(Date)
            return new CronTrigger(CRON).nextExecutionTime(triggerContext);
        });
    }

    private void doJobMethod() {
        // 任务逻辑
        log.info("dynamicCronTask is running...");
    }
}