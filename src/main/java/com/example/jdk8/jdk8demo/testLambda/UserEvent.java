package com.example.jdk8.jdk8demo.testLambda;

import java.util.EventObject;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/1/26
 * @since 3.0.1
 */
public class UserEvent extends EventObject {

    private String userName;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public UserEvent(Object source) {
        super(source);
    }


}
