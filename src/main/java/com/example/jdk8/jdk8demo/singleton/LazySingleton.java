package com.example.jdk8.jdk8demo.singleton;

import sun.security.jca.GetInstance;

/**
 * @author Collin
 * @date 2021/6/24 4:21 下午
 */
public class LazySingleton {

    public static volatile LazySingleton lazySingleton = null;

    private LazySingleton() {

    }

    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    lazySingleton= new LazySingleton();
                }
            }
        }
        return lazySingleton;
    }

}
