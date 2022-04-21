package com.example.jdk8.jdk8demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/4/20
 * @since 3.0.1
 */
@Slf4j
public class TestPutIf {


    private static AtomicBoolean system = new AtomicBoolean(false);


    private static Map<String, Long> map = new ConcurrentHashMap<>();

}
