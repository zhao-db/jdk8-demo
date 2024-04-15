package com.example.jdk8.jdk8demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jdk8.jdk8demo.schedule.SpringDynamicCronTask;

@SpringBootApplication
@EnableScheduling
@RestController
public class Jdk8DemoApplication {
    @Autowired
    private SpringDynamicCronTask task;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Jdk8DemoApplication.class, args);
        SpringDynamicCronTask task = applicationContext.getBean(SpringDynamicCronTask.class);
        task.startScheduleJob();
    }



    @RequestMapping("/")
    public void updateCron(String cron) {
        task.setCRON(cron);
    }

}
