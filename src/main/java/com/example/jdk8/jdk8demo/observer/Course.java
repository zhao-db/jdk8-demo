package com.example.jdk8.jdk8demo.observer;

import lombok.Data;

import java.util.Observable;

/**
 * @author Collin
 * @date 2021/5/8 7:12 下午
 */
@Data
public class Course extends Observable {

    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void createQuestion(Course course,Question question) {
        System.out.println(question.getUserName()+"在"+course.getCourseName()+"提出了一个问题，内容是"+ question.getQuestionContents());
        setChanged();
        notifyObservers(question);
    }

}
