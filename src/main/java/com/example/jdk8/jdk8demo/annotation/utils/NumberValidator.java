package com.example.jdk8.jdk8demo.annotation.utils;

import java.lang.reflect.Field;
import java.util.Objects;

import com.example.jdk8.jdk8demo.annotation.NumberCheck;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/8
 * @since 3.0.1
 */
public final class NumberValidator {

    public NumberValidator() {

    }

    public static <T> void validator(T t) throws IllegalAccessException {
        Integer filedValue = null;
        if (Objects.nonNull(t)) {
            Class<?> clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(NumberCheck.class)) {
                    field.setAccessible(true);
                    Object anInt = field.get(t);
                    filedValue = (int) anInt;
                    if (filedValue < 0 || filedValue > 100) {
                        throw new RuntimeException("请输入正确的年龄");
                    }
                }
            }

        }

    }

}
