package com.example.jdk8.jdk8demo.testLambda;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class User {
    private int id;
    private String username;
    private String password;
    private Integer age;

    private static String StringAsPointAfterTwo(String str) {
        BigDecimal bigDecimal = new BigDecimal(str).setScale(2, RoundingMode.HALF_UP);
        System.out.println(bigDecimal);
        BigDecimal v1 = new BigDecimal(str);
        v1.setScale(2, RoundingMode.HALF_UP);
        System.out.println(v1);

        return bigDecimal.toString();
    }

    public static void main(String[] args) {

        Integer num = 128;
        Integer nums = 129;
        System.out.println(num.equals(nums));
        System.out.println(Integer.valueOf(num).equals(Integer.valueOf(nums)));
        System.out.println(num > nums);
        System.out.println(num < nums);
    }

}
