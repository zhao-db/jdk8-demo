package com.example.jdk8.jdk8demo.algorithm.astar.method;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.jdk8.jdk8demo.algorithm.astar.AStarAlgorithm;
import com.example.jdk8.jdk8demo.algorithm.astar.entity.Grid;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/9/15
 * @since 3.0.1
 */
public class AStartSearch {

    private static final Integer[][] MAPS = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0},
    };

    private Grid AStarSearch(Grid startGrid, Grid endGrid) {
        List<Grid> openList = new ArrayList<>();
        List<Grid> closeList = new ArrayList<>();
        openList.add(startGrid);
        while (openList.size() > 0) {
            Grid currentGrid = this.findMinGrid(openList);
            openList.remove(currentGrid);
            closeList.add(currentGrid);
            List<Grid> neighborGrid = this.findNeighborGrid(currentGrid, openList, closeList);
            for (Grid grid : neighborGrid) {
                if (!openList.contains(grid)) {
                    grid.initGrid(currentGrid, endGrid);
                    openList.add(grid);
                }
            }

            for (Grid grid : openList) {
                if (grid.getX() == endGrid.getX() && grid.getY() == endGrid.getY()) {
                    return grid;
                }
            }

        }
        return null;
    }

    private Grid findMinGrid(List<Grid> openList) {
        Grid tempGrid = openList.get(0);
        for (Grid grid : openList) {
            if (grid.getF() < tempGrid.getF()) {
                tempGrid = grid;
            }
        }
        return tempGrid;
    }

    private List<Grid> findNeighborGrid(Grid current, List<Grid> openList, List<Grid> closeGrid) {
        List<Grid> gridList = new ArrayList<>();
        if (isValidGrid(current.getX() - 1, current.getY(), openList, closeGrid)) {
            gridList.add(new Grid(current.getX() - 1, current.getY()));
        }
        if (isValidGrid(current.getX() + 1, current.getY(), openList, closeGrid)) {
            gridList.add(new Grid(current.getX() + 1, current.getY()));
        }
        if (isValidGrid(current.getX(), current.getY() - 1, openList, closeGrid)) {
            gridList.add(new Grid(current.getX(), current.getY() - 1));
        }
        if (isValidGrid(current.getX(), current.getY() + 1, openList, closeGrid)) {
            gridList.add(new Grid(current.getX(), current.getY() + 1));
        }
        return gridList;
    }

    private boolean isValidGrid(int x, int y, List<Grid> openList, List<Grid> closeGrid) {
        if (x < 0 || x >= MAPS.length || y < 0 || y >= MAPS[0].length) {
            return false;
        }
        if (MAPS[x][y] == 1) {
            return false;
        }
        if (containGrid(x, y, openList)) {
            return false;
        }
        if (containGrid(x, y, closeGrid)) {
            return false;
        }
        return true;
    }

    private boolean containGrid(int x, int y, List<Grid> gridList) {
        for (Grid grid : gridList) {
            if (x == grid.getX() && y == grid.getY()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AStartSearch search = new AStartSearch();
        Grid start = new Grid(2, 1);
        Grid end = new Grid(3, 6);
        Grid result = search.AStarSearch(start, end);
        List<Grid> path = new ArrayList<>();
        while (Objects.nonNull(result)) {
            path.add(new Grid(result.getX(), result.getY()));
            result = result.getParent();
        }
        for (Grid grid : path) {
            System.out.println("(x:" + grid.getX() + " , y:" + grid.getY() + ")");
        }
        for (int i = 0; i < MAPS.length; i++) {
            for (int j = 0; j < MAPS[0].length; j++) {
                if (search.containGrid(i, j, path)) {
                    System.out.print("*, ");
                } else {
                    System.out.print(MAPS[i][j] + ", ");
                }
            }
            System.out.println();
        }

    }

}
