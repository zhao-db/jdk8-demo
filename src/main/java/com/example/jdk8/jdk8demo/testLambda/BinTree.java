package com.example.jdk8.jdk8demo.testLambda;

import com.alibaba.fastjson.JSONObject;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @author Collin
 * @date 2021/6/30 10:17 上午
 */
@ToString
public class BinTree {

    private BinTree left;
    private BinTree right;
    private Object data;

    public BinTree() {

    }

    public BinTree(BinTree left, BinTree right, Object data) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinTree(Object data) {
        this(null, null, data);
    }

    public static BinTree createTree(Object[] objects) {
        int[] array = {0,1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<BinTree> temps = new ArrayList<BinTree>(array.length / 2);
        for (int i = 0; i < array.length; i++) {
            BinTree node = new BinTree(array);
            node.data = array[i];
            temps.add(node);
        }
        for (int i = 0; i < array.length / 2 - 1; i++) {
            BinTree binTree = temps.get(i);
            binTree.left = temps.get(2 * i + 1);
            binTree.right = temps.get(2 * i + 2);
        }
        int lastNode = array.length / 2 - 1;
        BinTree node = temps.get(lastNode);
        // 添加左子树
        node.left = temps.get(lastNode * 2 + 1);
        if (array.length % 2 != 0) {
            node.right = temps.get(lastNode * 2 + 2);
        }
        BinTree binTree = temps.get(0);
        temps.clear();
        temps = null;
        return binTree;
    }

    // 前序遍历打印二叉树
    public static void prOrderTree(BinTree node) {
        if (node == null) {
            return;
        }
        System.out.print("\t" + node.data);
        // 左子树
        BinTree left = node.left;
        // 打印节点信息
        if (left != null) {
            prOrderTree(left);
        }
        // 右子树
        BinTree right = node.right;
        if (right != null) {
            prOrderTree(right);
        }
    }

    // 中序遍历
    public static void inOrderTree(BinTree node) {
        if (node == null) {
            return;
        }
        // 左子树
        BinTree left = node.left;
        if (left != null) {
            inOrderTree(left);
        }
        // 打印节点信息
        System.out.print("\t" + node.data);
        // 右子树
        BinTree right = node.right;
        if (right != null) {
            inOrderTree(right);
        }

    }

    public static void main(String[] args) {
        BinTree b = createTree(null);
        System.out.println("b.toString() = " + JSONObject.toJSONString(b));
    }

}
