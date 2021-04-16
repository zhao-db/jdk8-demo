package com.example.jdk8;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/11/24 15:19
 * @Version 1.0
 */
public class TestMain {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        /*Class aClass = Class.forName("com.example.jdk8.TestReflect");
        TestReflect tf = (TestReflect) aClass.newInstance();
        tf.setAge(1);
        Field field = tf.getClass().getDeclaredField("name");
        field.setAccessible(true);
        System.out.println(field.getName());
        field.set(tf, "zhaodb");
        System.out.println(tf.toString());*/
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list.toString());
        list.set(2, "ss");
        System.out.println(list.toString());
        list.add(2, "333");
        System.out.println(list.toString());

    }

}
