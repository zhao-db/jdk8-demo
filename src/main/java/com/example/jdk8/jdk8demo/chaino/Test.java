package com.example.jdk8.jdk8demo.chaino;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2021/10/27
 * @since 3.0.1
 */
public class Test {
    public static void main(String[] args) {
        Approver articleApprover = new ArticleApprover();
        Approver videoApprover = new VideoApprover();
        Course course = new Course();
        course.setName("debug 设计模式");
        course.setArticle("");
        course.setVideo("");
        articleApprover.setNextApprover(videoApprover);
        articleApprover.deploy(course);
    }
}
