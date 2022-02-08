package com.example.jdk8.jdk8demo.sometest;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/8 15:15
 * @Version 1.0
 */
public class TestMain {

    //public String getString() throws ExecutionException, InterruptedException {
     /*   List<Future<String>> futureList = new CopyOnWriteArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("开始执行 循环");
        for (int i = 0; i < 10; i++) {
            MyThread my = new MyThread();
            Future<String> result = executorService.submit(my);
            futureList.add(result);
            System.out.println("en ==== >" + i);
        }
        for (Future<String> f : futureList) {
            System.out.println("callback:++++++++++++++++++++++++" + f.get());
        }
        System.out.println("main=====");
        return "";*/
//    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String s = "ADD";
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        System.out.println(bytes);
       /* TestMain t = new TestMain();
        t.getString();*/
    }

    /*   public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
           Strategry str1 = StrategryFactory.getStrategry("ALIPAY");
           str1.pay(BigDecimal.TEN);
           Strategry str2 = StrategryFactory.getStrategry("WECHATPAY");
           str2.pay(BigDecimal.TEN);
           Strategry str3 = StrategryFactory.getStrategry("ICBCPAY");
           str3.pay(BigDecimal.TEN);

       }*/
   /* public static List<Integer> getList() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
       *//* List<Integer> list = getList();
        int num = 5;
        System.out.println("list.get(3) = " + list.get(3));
*//*
        int i = 0;
        while (true) {
            User u = new User();
            u.setId(1);
            System.out.println(i++);
        }

    }*/

}
