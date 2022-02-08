package com.example.jdk8.jdk8demo.chaino;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2021/10/27
 * @since 3.0.1
 */
@Data
public abstract class Approver {

    protected Approver approver;

    public void setNextApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void deploy(Course course);

}
