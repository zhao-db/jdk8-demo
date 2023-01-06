package com.example.jdk8.jdk8demo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/12/2
 * @since 3.0.1
 */
public class TestList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        System.out.println("list = " + list);
        String remove = list.remove(0);
        System.out.println("remove = " + remove);
        System.out.println("list = " + list);
    }
}
