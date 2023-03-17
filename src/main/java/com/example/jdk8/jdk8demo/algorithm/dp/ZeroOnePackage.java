package com.example.jdk8.jdk8demo.algorithm.dp;

/**
 * <p>
 * 背包问题 回溯算法
 * </p>
 *
 * @author zhaodb 2023/3/10
 * @since 3.0.1
 */
public class ZeroOnePackage {

    /*价值数组*/
    private static int[] values = {10, 5, 20, 2, 14, 23};
    /*重量数组*/
    private static int[] weights = {15, 25, 40, 20, 15, 24};
    /*背包承重量*/
    private static int capcity = 40;
    /*物品个数*/
    private static int count = weights.length;
    /*当前背包中物品的重量*/
    private static int currentWeight = 0;
    /*当前背包中物品的价值*/
    private static int currentValue = 0;
    /*目前最优装载的价值*/
    static int bestValue = 0;
    /*未装入背包的物品价值*/
    private static int leftValue;
    /*当前解*/
    private static int[] currentResult = new int[count];
    /*最终解*/
    private static int[] bestResult = new int[count];

    public static void findMaxValue(int num) {
        /*到达叶结点，即已经遍历到序列汇总最后一个值，需要比较当前解与之前存储最优解的关系，然后结束此次遍历*/
        if (num == count) {
            if (currentValue > bestValue) {
                for (int i = 0; i < count; i++) {
                    bestResult[i] = currentResult[i];
                }
                bestValue = currentValue;
            }
            return;
        }

        /*开始尝试装载第num+1个物品，leftValue需要减去当前物品value*/
        leftValue = leftValue - values[num];
        if (currentWeight + weights[num] <= capcity) {
            /*能装下该物品的话， 标识当前解数组中该物品的值为1，然后currentValue加入当前物品的value
             *currentWeight加入当前物品的重量*/
            currentResult[num] = 1;
            currentValue = currentValue + values[num];
            currentWeight = currentWeight + weights[num];
            /*回溯*/
            findMaxValue(num + 1);
            /*在currentValue和currentWeight减去当前物品的影响，回溯现场*/
            currentValue = currentValue - values[num];
            currentWeight = currentWeight - weights[num];
        }
        /*恢复现场*/
        currentResult[num] = 0;
        findMaxValue(num + 1);
        leftValue += values[num];
    }

    public static void main(String[] args) {
        for (int i = 0; i < values.length; i++) {
            leftValue += values[i];
        }
        findMaxValue(0);
        System.out.println("最优装载价值为：" + bestValue);
        for (int i = 0; i < count; i++) {
            if (bestResult[i] == 1) {
                System.out.println("装载第" + (i + 1) + "件物品，这件物品的价值是：" + values[i]);
            }
        }
    }
}
