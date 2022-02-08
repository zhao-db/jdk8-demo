package com.example.jdk8.jdk8demo.chaino;

import org.springframework.util.StringUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2021/10/27
 * @since 3.0.1
 */
public class ArticleApprover extends Approver {
    @Override
    public void deploy(Course course) {
        if (!StringUtils.isEmpty(course.getArticle())) {
            System.out.println("含有手机" + course.getName());
            if (approver != null) {
                approver.deploy(course);
            }
        } else {
            System.out.println("不含手机" + course.getName());
            return;
        }
    }
}
