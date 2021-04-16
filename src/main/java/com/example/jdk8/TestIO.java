package com.example.jdk8;

import java.io.*;

/**
 * @Description
 * @Author zhaodb
 * @Date 2021/4/8 15:55
 * @Version 1.0
 */
public class TestIO {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\天龙小蜜\\清包日志\\天河．╰°.txt");
        FileInputStream inputStream = new FileInputStream(file);
        int len;
        while ((len = inputStream.read()) != -1) {
            System.out.println( len);
        }
    }

}
