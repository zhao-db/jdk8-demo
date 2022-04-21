package com.example.jdk8.jdk8demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/3/28
 * @since 3.0.1
 */
public class TestMain {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("ATHENA-RPC");
        stringList.add("ARK-RPC");
        stringList.add("TEST-RPC");
        String collect = stringList.stream().collect(Collectors.joining(","));
        System.out.println("collect = " + collect);
    }

}
