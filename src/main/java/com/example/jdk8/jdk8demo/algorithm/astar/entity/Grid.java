package com.example.jdk8.jdk8demo.algorithm.astar.entity;

import java.util.Objects;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/9/15
 * @since 3.0.1
 */
@Data
public class Grid {

    private int x;
    private int y;
    private int f;
    private int g;
    private int h;
    private Grid parent;

    public Grid(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void initGrid(Grid parent, Grid entGrid) {
        this.parent = parent;
        if (Objects.nonNull(parent)) {
            this.g = parent.getG();
        } else {
            this.g = 1;
        }
        this.h = Math.abs(this.x - entGrid.x) + Math.abs(this.y - entGrid.y);
        this.f = this.g + this.h;
    }

}
