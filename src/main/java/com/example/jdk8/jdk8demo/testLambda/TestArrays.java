package com.example.jdk8.jdk8demo.testLambda;

/**
 * @author Collin
 * @date 2021/7/9 9:51 上午
 */
public class TestArrays {
    public static int step = 1;

    public static StringBuffer printStep = new StringBuffer();


    public static int[][] maze = {
            {1, 1, 1, 1},

            {1, 0, 1, 0},

            {1, 0, 1, 0},

            {1, 0, 0, 0},
    };

    public static void main(String[] args) {
        int i, j;
        TestArrays.way(1, 1);
        System.out.println("这是迷宫图表");
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                System.out.print("  " + maze[i][j] + "-‖");
            }
            System.out.println(" ");
        }
    }

    public static boolean way(int x, int y) {
        //代表递归终止条件（也就是当走出出口时标记为 2）
        if (maze[4][4] == 2) {
            return true;
        } else {
            if (maze[y][x] == 0) {
                maze[y][x] = 2;
                /*
                 * 下面if判断条件代表当前坐标为基点，
                 * 根据判断对当前位置进行递归调用：如：
                 * 往上、往右上、往右、往右下、往下、
                 * 往左下、往左、往左上的坐标是否可走，
                 * 判断是否可走的返回条件是：
                 * 2代表可通过、1代表不能通过、3表示已经走过，但是未能走通。
                 */

                if (way(x, y - 1)) {
                    printStep.append("第 " + step + " 步的所走的位置是 x = " + x + " y = " + y + "\n");
                    step++;
                    return true;
                } else if (way(x + 1, y - 1)) {
                    printStep.append("第 " + step + " 步的所走的位置是 x = " + x + " y = " + y + "\n");
                    step++;
                    return true;
                } else if (way(x + 1, y)) {
                    printStep.append("第 " + step + " 步的所走的位置是 x = " + x + " y = " + y + "\n");
                    step++;
                    return true;
                } else if (way(x + 1, y + 1)) {
                    printStep.append("第 " + step + " 步的所走的位置是 x = " + x + " y = " + y + "\n");
                    step++;
                    return true;
                } else if (way(x, y + 1)) {
                    printStep.append("第 " + step + " 步的所走的位置是 x = " + x + " y = " + y + "\n");
                    step++;
                    return true;
                } else if (way(x - 1, y + 1)) {
                    printStep.append("第 " + step + " 步的所走的位置是 x = " + x + " y = " + y + "\n");
                    step++;
                    return true;
                } else if (way(x - 1, y)) {
                    printStep.append("第 " + step + " 步的所走的位置是 x = " + x + " y = " + y + "\n");
                    step++;
                    return true;
                } else if (way(x - 1, y - 1)) {
                    printStep.append("第 " + step + " 步的所走的位置是 x = " + x + " y = " + y + "\n");
                    step++;
                    return true;
                } else {
                    maze[y][x] = 3;
                    return false;
                }
            } else {
                return false;
            }

        }

    }

}
