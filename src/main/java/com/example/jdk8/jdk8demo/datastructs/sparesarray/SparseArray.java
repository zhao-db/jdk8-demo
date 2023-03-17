package com.example.jdk8.jdk8demo.datastructs.sparesarray;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/3/17
 * @since 3.0.1
 */
public class SparseArray {

    public static void main(String[] args) {
        //11*11原始数组 0表示没有棋子 1表示黑子 2表示2蓝子
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        for (int[] raw : chessArray) {
            for (int data : raw) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //二维数组转稀疏数组
        //先遍历二维数组获取非0的个数
        int sum = 0;
        int columnLength = chessArray.length;
        int rawLength = chessArray[0].length;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);
        //创建稀疏数组
        int[][] sparesArray = new int[sum + 1][3];
        //给稀疏数组赋值
        sparesArray[0][0] = columnLength;
        sparesArray[0][1] = rawLength;
        sparesArray[0][2] = sum;
        int count = 0;//记录第几条非零数据
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparesArray[count][0] = i;
                    sparesArray[count][1] = j;
                    sparesArray[count][2] = chessArray[i][j];
                }
            }
        }
        for (int i = 0; i < sparesArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t", sparesArray[i][0], sparesArray[i][1], sparesArray[i][2]);
        }

        //稀疏数组复原二维数组
        int[][] sourceArray = new int[sparesArray[0][0]][sparesArray[0][1]];
        for (int i = 1; i < sparesArray.length; i++) {
            sourceArray[sparesArray[i][0]][sparesArray[i][1]] = sparesArray[i][2];
        }
        System.out.println("sourceArray = " + sourceArray);
        for (int[] raw : sourceArray) {
            for (int data : raw) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }


}
