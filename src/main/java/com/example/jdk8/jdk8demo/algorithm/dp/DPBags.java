package com.example.jdk8.jdk8demo.algorithm.dp;

/**
 * <p>
 * 动态规划 背包问题
 * </p>
 *
 * @author zhaodb 2023/3/10
 * @since 3.0.1
 */
public class DPBags {

    public static int[][] _01bagTwoArray(int[] value, int[] weight, int bagSize) {
        int length = value.length;
        int value0 = 0;
        int[][] dp = new int[length + 1][bagSize + 1];
        //背包容量为0 啥也不能装
        for (int i = 0; i < length; i++) {
            dp[i][0] = value0;
        }
        //只获取第一个物品时 背包容量变化
        for (int i = weight[0]; i < bagSize; i++) {
            dp[0][i] = value[0];
        }
        for (int i = 1; i < length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i]);
                }
            }
        }
        return dp;
    }

    /**
     * 01背包问题
     *
     * @param value   物品价值
     * @param weight  物品重量
     * @param bagSize 背包容量
     * @return
     */
    public static int _01Bag(int[] value, int[] weight, int bagSize) {

        //物品数量
        int length = value.length;
        //背包容量
        int[] dp = new int[bagSize + 1];
        //遍历物品个数
        for (int i = 0; i < length; i++) {
            //遍历背包剩余容量
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bagSize];
    }

    public static void main(String[] args) {
        int[] value = {2, 3, 6};
        int[] weight = {1, 3, 15};
//        int i = _01Bag(value, weight, 4);
//        System.out.println("i = " + i);
        int[][] ints = _01bagTwoArray(value, weight, 4);
        for (int i = 0; i < value.length + 1; i++) {
            System.out.println("i = " + ints[i][i]);;
        }
    }

}
