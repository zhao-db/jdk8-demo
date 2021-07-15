package com.example.jdk8.jdk8demo;

import com.example.jdk8.jdk8demo.enums.ColorEnum;
import com.example.jdk8.jdk8demo.pay.Strategry;
import com.example.jdk8.jdk8demo.pay.StrategryFactory;
import com.example.jdk8.jdk8demo.testLambda.User;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/9/8 15:15
 * @Version 1.0
 */
public class TestMain {

    /*   public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
           Strategry str1 = StrategryFactory.getStrategry("ALIPAY");
           str1.pay(BigDecimal.TEN);
           Strategry str2 = StrategryFactory.getStrategry("WECHATPAY");
           str2.pay(BigDecimal.TEN);
           Strategry str3 = StrategryFactory.getStrategry("ICBCPAY");
           str3.pay(BigDecimal.TEN);

       }*/
    public static List<Integer> getList() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) throws InterruptedException {
       /* List<Integer> list = getList();
        int num = 5;
        System.out.println("list.get(3) = " + list.get(3));
*/
        int i = 0;
        while (true) {
            User u = new User();
            u.setId(1);
            System.out.println(i++);
        }

    }

}
