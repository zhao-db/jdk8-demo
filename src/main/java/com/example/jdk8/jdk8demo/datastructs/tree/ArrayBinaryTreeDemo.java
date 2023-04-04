package com.example.jdk8.jdk8demo.datastructs.tree;

/**
 * <p>
 * 顺序存储二叉树
 * 顺序存储二叉树的特点:
 * 顺序二叉树通常只考虑完全二叉树
 * 第n个元素的左子节点为  2 * n + 1
 * 第n个元素的右子节点为  2 * n + 2
 * 第n个元素的父节点为  (n-1) / 2
 * </p>
 *
 * @author zhaodb 2023/4/4
 * @since 3.0.1
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(array);
        tree.preOrder();
    }
}

class ArrayBinaryTree {
    //存储数据节点
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 编写一个方法 完成有序数组的前序遍历
     *
     * @param index 数组下标
     */
    public void preOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
        }
        System.out.println(array[index]);
        if (index * 2 + 1 < array.length) {
            preOrder(index * 2 + 1);
        }
        if (index * 2 + 2 < array.length) {
            preOrder(index * 2 + 2);
        }
    }

    /**
     * 编写一个方法 完成有序数组的前序遍历
     *
     * @param index 数组下标
     */
    public void infixOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
        }
        if (index * 2 + 1 < array.length) {
            preOrder(index * 2 + 1);
        }
        System.out.println(array[index]);
        if (index * 2 + 2 < array.length) {
            preOrder(index * 2 + 2);
        }
    }

    /**
     * 编写一个方法 完成有序数组的前序遍历
     *
     * @param index 数组下标
     */
    public void postOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
        }
        if (index * 2 + 1 < array.length) {
            preOrder(index * 2 + 1);
        }
        if (index * 2 + 2 < array.length) {
            preOrder(index * 2 + 2);
        }
        System.out.println(array[index]);
    }
}


