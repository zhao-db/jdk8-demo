package com.example.jdk8.jdk8demo.datastructs.recursion;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/3/29
 * @since 3.0.1
 */
public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
//        map[2][2] = 1;
        System.out.println("开始地图");
        printMap(map);
        getWay(map, 1, 1);
        System.out.println("结束后地图");
        printMap(map);
    }

    /**
     * @param map    地图
     * @param startX 开始点位
     * @param startY 开始点位
     * @return 找到路径返回成功  1代表墙路不通 2代表已走过 3代表已走过不通  设置一个策略 先走下 再走右 再走上 再走左
     */
    private static boolean getWay(int[][] map, int startX, int startY) {
        if (map[6][5] == 2) {
            return true;
        }
        if (map[startX][startY] == 0) {
            map[startX][startY] = 2;
            if (getWay(map, startX + 1, startY)) {
                return true;
            } else if (getWay(map, startX, startY + 1)) {
                return true;
            } else if (getWay(map, startX - 1, startY)) {
                return true;
            } else if (getWay(map, startX, startY - 1)) {
                return true;
            } else {
                map[startX][startY] = 3;
                return false;
            }
        } else {
            return false;
        }
    }

    private static void printMap(int[][] map) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
