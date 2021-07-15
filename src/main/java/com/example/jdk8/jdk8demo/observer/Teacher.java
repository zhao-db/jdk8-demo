package com.example.jdk8.jdk8demo.observer;

import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Collin
 * @date 2021/5/8 7:13 下午
 */
@Data
public class Teacher implements Observer {

    private String teacherName;

    public Teacher(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("进入");
        Course c = (Course) o;
        Question q = (Question) arg;
        System.out.println(teacherName + "的" + c.getCourseName() + "收到了" + q.getUserName() + "一个提问" + q.getQuestionContents());
    }
}
