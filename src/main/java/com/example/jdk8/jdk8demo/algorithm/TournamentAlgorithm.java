package com.example.jdk8.jdk8demo.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * 锦标赛算法实现
 * Tournament Algorithm - 用于高效地找到数组中的最大值或最小值
 *
 * 算法原理：
 * 1. 将数组元素两两配对进行比较，类似体育锦标赛
 * 2. 每轮比较后，胜出的元素进入下一轮
 * 3. 重复这个过程直到找到最终的胜者
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(log n) - 由于递归调用栈
 *
 * @author GitHub Copilot
 */
public class TournamentAlgorithm {

    /**
     * 锦标赛节点类 - 用于构建锦标赛树
     */
    static class TournamentNode {
        int value;           // 节点存储的值
        int index;           // 原数组中的索引位置
        TournamentNode left; // 左子节点
        TournamentNode right;// 右子节点

        public TournamentNode(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public TournamentNode(TournamentNode left, TournamentNode right) {
            this.left = left;
            this.right = right;
            // 比较左右子节点，选择较大的值作为父节点的值
            if (left.value >= right.value) {
                this.value = left.value;
                this.index = left.index;
            } else {
                this.value = right.value;
                this.index = right.index;
            }
        }
    }

    /**
     * 使用递归方式实现锦标赛算法找最大值
     *
     * @param arr 输入数组
     * @return 数组中的最大值
     */
    public static int findMaxByRecursion(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }

        // 构建锦标赛树并返回根节点（包含最大值）
        TournamentNode root = buildTournamentTree(arr, 0, arr.length - 1);
        return root.value;
    }

    /**
     * 递归构建锦标赛树
     *
     * @param arr 输入数组
     * @param left 左边界索引
     * @param right 右边界索引
     * @return 锦标赛树的根节点
     */
    private static TournamentNode buildTournamentTree(int[] arr, int left, int right) {
        // 基础情况：只有一个元素时，直接创建叶子节点
        if (left == right) {
            return new TournamentNode(arr[left], left);
        }

        // 分治：将数组分为两部分
        int mid = left + (right - left) / 2;

        // 递归构建左子树
        TournamentNode leftChild = buildTournamentTree(arr, left, mid);
        // 递归构建右子树
        TournamentNode rightChild = buildTournamentTree(arr, mid + 1, right);

        // 合并：创建父节点，比较左右子节点的值
        return new TournamentNode(leftChild, rightChild);
    }

    /**
     * 使用迭代方式实现锦标赛算法找最大值
     * 这种方法模拟了真实的锦标赛过程
     *
     * @param arr 输入数组
     * @return 数组中的最大值
     */
    public static int findMaxByIteration(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }

        // 将原数组复制到工作列表中
        List<Integer> tournament = new ArrayList<>();
        for (int value : arr) {
            tournament.add(value);
        }

        System.out.println("初始参赛选手: " + tournament);

        int round = 1;
        // 进行锦标赛，每轮淘汰一半选手
        while (tournament.size() > 1) {
            List<Integer> nextRound = new ArrayList<>();

            System.out.println("第 " + round + " 轮比赛开始，当前选手数: " + tournament.size());

            // 两两配对进行比较
            for (int i = 0; i < tournament.size(); i += 2) {
                if (i + 1 < tournament.size()) {
                    // 有配对的情况：比较两个元素，较大者胜出
                    int player1 = tournament.get(i);
                    int player2 = tournament.get(i + 1);
                    int winner = Math.max(player1, player2);
                    nextRound.add(winner);
                    System.out.println("  " + player1 + " vs " + player2 + " -> 胜者: " + winner);
                } else {
                    // 没有配对的情况：直接晋级（轮空）
                    int player = tournament.get(i);
                    nextRound.add(player);
                    System.out.println("  " + player + " 轮空晋级");
                }
            }

            tournament = nextRound;
            System.out.println("第 " + round + " 轮结束，晋级选手: " + tournament);
            round++;
        }

        return tournament.get(0);
    }

    /**
     * 锦标赛算法找最小值
     *
     * @param arr 输入数组
     * @return 数组中的最小值
     */
    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }

        return findMinRecursive(arr, 0, arr.length - 1);
    }

    /**
     * 递归找最小值的辅助方法
     */
    private static int findMinRecursive(int[] arr, int left, int right) {
        // 基础情况：只有一个元素
        if (left == right) {
            return arr[left];
        }

        // 基础情况：只有两个元素
        if (right - left == 1) {
            return Math.min(arr[left], arr[right]);
        }

        // 分治：分为两部分
        int mid = left + (right - left) / 2;
        int leftMin = findMinRecursive(arr, left, mid);
        int rightMin = findMinRecursive(arr, mid + 1, right);

        // 合并：返回较小值
        return Math.min(leftMin, rightMin);
    }

    /**
     * 锦标赛算法同时找最大值和最小值
     * 这是锦标赛算法的一个优化应用
     *
     * @param arr 输入数组
     * @return 包含最大值和最小值的结果对象
     */
    public static MinMaxResult findMinMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }

        return findMinMaxRecursive(arr, 0, arr.length - 1);
    }

    /**
     * 最大最小值结果类
     */
    public static class MinMaxResult {
        public final int min;
        public final int max;

        public MinMaxResult(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "MinMaxResult{min=" + min + ", max=" + max + "}";
        }
    }

    /**
     * 递归找最大最小值的辅助方法
     */
    private static MinMaxResult findMinMaxRecursive(int[] arr, int left, int right) {
        // 基础情况：只有一个元素
        if (left == right) {
            return new MinMaxResult(arr[left], arr[left]);
        }

        // 基础情况：只有两个元素
        if (right - left == 1) {
            int min = Math.min(arr[left], arr[right]);
            int max = Math.max(arr[left], arr[right]);
            return new MinMaxResult(min, max);
        }

        // 分治：分为两部分
        int mid = left + (right - left) / 2;
        MinMaxResult leftResult = findMinMaxRecursive(arr, left, mid);
        MinMaxResult rightResult = findMinMaxRecursive(arr, mid + 1, right);

        // 合并：比较两部分的结果
        int finalMin = Math.min(leftResult.min, rightResult.min);
        int finalMax = Math.max(leftResult.max, rightResult.max);

        return new MinMaxResult(finalMin, finalMax);
    }

    /**
     * 测试锦标赛算法的主方法
     */
    public static void main(String[] args) {
        // 测试数据
        int[] testArray = {64, 34, 25, 12, 22, 11, 90, 88, 76, 50, 42};

        System.out.println("=== 锦标赛算法测试 ===");
        System.out.println("测试数组: " + Arrays.toString(testArray));
        System.out.println();

        // 测试递归方式找最大值
        System.out.println("1. 递归方式找最大值:");
        int maxByRecursion = findMaxByRecursion(testArray);
        System.out.println("最大值: " + maxByRecursion);
        System.out.println();

        // 测试迭代方式找最大值
        System.out.println("2. 迭代方式找最大值 (模拟锦标赛过程):");
        int maxByIteration = findMaxByIteration(testArray);
        System.out.println("最终冠军 (最大值): " + maxByIteration);
        System.out.println();

        // 测试找最小值
        System.out.println("3. 找最小值:");
        int min = findMin(testArray);
        System.out.println("最小值: " + min);
        System.out.println();

        // 测试同时找最大最小值
        System.out.println("4. 同时找最大最小值:");
        MinMaxResult minMax = findMinMax(testArray);
        System.out.println("结果: " + minMax);
        System.out.println();

        // 性能对比测试
        System.out.println("5. 性能对比测试:");
        performanceTest();
    }

    /**
     * 性能测试方法
     * 比较锦标赛算法与简单遍历的性能
     */
    private static void performanceTest() {
        int[] largeArray = new int[100000];
        // 填充随机数据
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = (int) (Math.random() * 1000000);
        }

        // 测试锦标赛算法
        long startTime = System.nanoTime();
        int tournamentMax = findMaxByRecursion(largeArray);
        long tournamentTime = System.nanoTime() - startTime;

        // 测试简单遍历
        startTime = System.nanoTime();
        int simpleMax = Arrays.stream(largeArray).max().orElse(0);
        long simpleTime = System.nanoTime() - startTime;

        System.out.println("大数组 (10万元素) 性能测试:");
        System.out.println("锦标赛算法用时: " + tournamentTime / 1000000.0 + " ms, 结果: " + tournamentMax);
        System.out.println("简单遍历用时: " + simpleTime / 1000000.0 + " ms, 结果: " + simpleMax);
        System.out.println("结果是否一致: " + (tournamentMax == simpleMax));
    }
}
