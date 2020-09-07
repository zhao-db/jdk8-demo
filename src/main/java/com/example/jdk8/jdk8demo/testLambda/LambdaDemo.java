package com.example.jdk8.jdk8demo.testLambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaDemo {

    public static void main(String[] args) {
       /* List<User> list = new ArrayList<>();
        User user = new User();
        user.setPassword("sdf");
        user.setUsername("zz");
        User user1 = new User();
        user1.setPassword("sdf1");
        user1.setUsername("zz1");
        list.add(user1);
        list.add(user);
        System.out.println(list.toString());

        Map<String, Object> map = list.stream().collect(Collectors.toMap(User::getUsername, (x) -> x));
        System.out.println(map.toString());*/
        lambdaMap();
    }

    public static void lambdaMap() {
        User u = new User(1, "zdb", "331");
        User u1 = new User(1, "zdb1", "332");
        User u2 = new User(2, "zdb1", "332");
        List<User> list = new ArrayList<>();
        list.add(u);
        list.add(u1);
        list.add(u2);
        int tempId = 0;
        for (User us : list) {
            tempId = tempId != 0 ? tempId : us.getId();
            if (tempId != us.getId()) {
                System.out.println("不同");
                return;
            } else {
                System.out.println("相同");
            }


        }

    }
}