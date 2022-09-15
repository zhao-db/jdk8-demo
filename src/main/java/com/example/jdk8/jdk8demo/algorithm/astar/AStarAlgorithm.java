package com.example.jdk8.jdk8demo.algorithm.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/9/13
 * @since 3.0.1
 */
public class AStarAlgorithm {

    public static final int[][] MAZE = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };

    public static Grid aStarSearch(Grid start, Grid end) {
        List<Grid> openList = new ArrayList<>();
        List<Grid> closeList = new ArrayList<>();
        //把起点加入 openList
        openList.add(start);
        //主循环，每一轮检查1个当前方格节点
        while (openList.size() > 0) {
            // 在openList中查找 F值最小的节点，将其作为当前方格节点
            Grid currentGrid = findMinGrid(openList);
            // 将当前方格节点从openList中移除
            openList.remove(currentGrid);
            // 当前方格节点进入 closeList
            closeList.add(currentGrid);
            // 找到所有邻近节点
            List<Grid> neighbors = findNeighbors(currentGrid, openList, closeList);
            for (Grid grid : neighbors) {
                //邻近节点不在openList 中，标记“父节点”、G、H、F，并放入openList
                if (!openList.contains(grid)) {
                    grid.initGrid(currentGrid, end);
                    openList.add(grid);
                }
            }
            //如果终点在openList中，直接返回终点格子
            for (Grid grid : openList) {
                if (grid.x == end.x && grid.y == end.y) {
                    return grid;
                }
            }
        }
        //openList用尽，仍然找不到终点，说明终点不可到达，返回空
        return null;
    }

    private static Grid findMinGrid(List<Grid> openList) {
        Grid tempGrid = openList.get(0);
        for (Grid grid : openList) {
            if (grid.f < tempGrid.f) {
                tempGrid = grid;
            }
        }
        return tempGrid;
    }

    public static List<Grid> findNeighbors(Grid currentGrid, List<Grid> openList, List<Grid> closeList) {
        List<Grid> gridList = new ArrayList<>();
        if (isValidGrid(currentGrid.x, currentGrid.y - 1, openList, closeList)) {
            gridList.add(new Grid(currentGrid.x, currentGrid.y - 1));
        }
        if (isValidGrid(currentGrid.x, currentGrid.y + 1, openList, closeList)) {
            gridList.add(new Grid(currentGrid.x, currentGrid.y + 1));
        }
        if (isValidGrid(currentGrid.x - 1, currentGrid.y, openList, closeList)) {
            gridList.add(new Grid(currentGrid.x - 1, currentGrid.y));
        }
        if (isValidGrid(currentGrid.x + 1, currentGrid.y, openList, closeList)) {
            gridList.add(new Grid(currentGrid.x + 1, currentGrid.y));
        }
        return gridList;
    }

    public static boolean isValidGrid(int x, int y, List<Grid> openList, List<Grid> closeList) {
        //是否超过边界
        if (x < 0 || x >= MAZE.length || y < 0 || y >= MAZE[0].length) {
            return false;
        }
        //是否有障碍物
        if (MAZE[x][y] == 1) {
            return false;
        }
        //是否已经在openList中
        if (containGrid(openList, x, y)) {
            return false;
        }
        //是否已经在closeList中
        if (containGrid(closeList, x, y)) {
            return false;
        }
        return true;
    }

    public static boolean containGrid(List<Grid> gridList, int x, int y) {
        for (Grid grid : gridList) {
            if (grid.x == x && grid.y == y) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Grid start = new Grid(2, 1);
        Grid end = new Grid(2, 6);
        // 搜索迷宫终点
        Grid resultGrid = aStarSearch(start, end);
        // 回溯迷宫路径
        List<Grid> path = new ArrayList<>();
        while (Objects.nonNull(resultGrid)) {
            path.add(new Grid(resultGrid.x, resultGrid.y));
            resultGrid = resultGrid.parent;
        }
        for (Grid grid : path) {
            System.out.println("grid = " + JSONObject.toJSONString(grid));
        }
        for (int i = 0; i < MAZE.length; i++) {
            for (int j = 0; j < MAZE[0].length; j++) {
                if (containGrid(path, i, j)) {
                    System.out.print("*, ");
                } else {
                    System.out.print(MAZE[i][j] + ", ");
                }
            }
            System.out.println();
        }

    }

    @Data
    static class Grid {
        private int x;
        private int y;
        private int f;
        private int h;
        private int g;
        private Grid parent;

        public Grid(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void initGrid(Grid parent, Grid end) {
            this.parent = parent;
            if (parent != null) {
                this.g = parent.g + 1;
            } else {
                this.g = 1;
            }
            this.h = Math.abs(this.x - end.x) + Math.abs(this.y - end.y);
            this.f = this.g + this.h;
        }

    }

}
