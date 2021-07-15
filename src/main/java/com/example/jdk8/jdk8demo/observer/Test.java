package com.example.jdk8.jdk8demo.observer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * @author Collin
 * @date 2021/5/8 7:22 下午
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date start = sdf.parse("13:00");
        Date end = sdf.parse("23:00");
        Date now = sdf.parse(sdf.format(new Date()));
        if (start.after(end) || start.equals(end)) {
            if (now.getTime() >= start.getTime() || now.getTime() <= end.getTime()) {
                System.out.println("在时间范围内");
            }
        } else {
            if (now.getTime() >= start.getTime() && now.getTime() <= end.getTime()) {
                System.out.println("在时间范围内");
            }
        }
    }
}
