package com.example.jdk8.jdk8demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2025/7/25
 * @since 3.0.1
 */
public class JbsAlgo {

    public static void main(String[] args) {
        int[] testArray = {64, 34, 25, 12, 22, 11, 90, 88, 76, 50, 42};
        int max = findMax(testArray, 0, testArray.length - 1);
        System.out.println(max);
    }

    public static int findMax(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int mid = (left + right) / 2;
        int leftMax = findMax(arr, left, mid);
        int rightMax = findMax(arr, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }

    public static int findMaxIterator(int[] arr) {

        // 将原数组复制到工作列表中
        List<Integer> tournament = new ArrayList<>();
        for (int value : arr) {
            tournament.add(value);
        }
        List<Integer> nextRound = new ArrayList<>();
        int round = 1;
        for (int i = 0; i < tournament.size(); i += 2) {
            for (int j = i + 1; j < tournament.size(); j++) {
                int player1 = tournament.get(i);
                int player2 = tournament.get(j);
                int winner = Math.max(player1, player2);
                nextRound.add(winner);
                System.out.println("  " + player1 + " vs " + player2 + " -> 胜者: " + winner);
            }
        }

        return -1;
    }

}
