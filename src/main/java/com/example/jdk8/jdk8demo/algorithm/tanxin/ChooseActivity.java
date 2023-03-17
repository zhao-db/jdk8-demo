package com.example.jdk8.jdk8demo.algorithm.tanxin;

import java.util.ArrayList;
import java.util.List;

/*贪心算法，活动选择问题*/
public class ChooseActivity {
    public static void main(String[] args) {
        int[] startTime = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] endTime = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        /*迭代解法*/
        List<Integer> activityTime = activityChoice(startTime, endTime);
        for (Integer activity : activityTime)
            System.out.print(activity + 1 + " ");
        System.out.println();

        /*递归解法*/
        List<Integer> activityTime2 = new ArrayList<Integer>();
        activityTime2 = acivitySelector(startTime, endTime, 0, startTime.length - 1, activityTime2);
        for (Integer activity : activityTime2)
            System.out.print(activity + " ");

    }

    /*活动选择问题的迭代解法*/
    private static List<Integer> activityChoice(int[] startTime, int[] endTime) {
        List<Integer> list = new ArrayList<Integer>();
        /*因为活动已经按结束时间进行过升序排列，所以要默认选中第一个活动*/
        list.add(0);

        /*当前开始活动的结束时间*/
        int curTime = endTime[0];
        for (int i = 1; i < endTime.length; i++) {
            /*如果目前的活动的开始时间>=之前选择的活动的结束时间，则选取该活动，更新当前时间*/
            if (startTime[i] >= curTime) {
                list.add(i);
                curTime = endTime[i];
            }
        }
        return list;
    }

    /*活动选择问题的递归解法*/
    static List<Integer> acivitySelector(int[] startTime, int[] endTime, int i, int n, List<Integer> list) {
        /*因为活动已经按结束时间进行过升序排列，所以要默认选中第一个活动，+1是因为数组下标从0开始，此处是数组下标+1*/
        if (i == 0) {
            list.add(i + 1);
        }

        int j = i + 1;
        /*此处的循环是过滤掉不符合要求的活动*/
        while (j <= n && endTime[i] > startTime[j]) {
            j++;
        }
        //找到一个满足f[i]<=s[j]的活动，添加到list
        if (j <= n) {
            /*j+1对应活动的编号，也就是数组下标+1*/
            list.add(j + 1);
            /*更新一下当前活动j，然后继续向后找满足条件的活动*/
            acivitySelector(startTime, endTime, j, n, list);
        }
        return list;
    }
}