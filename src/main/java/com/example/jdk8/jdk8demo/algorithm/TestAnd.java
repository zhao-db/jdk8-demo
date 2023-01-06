package com.example.jdk8.jdk8demo.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.example.jdk8.jdk8demo.algorithm.treenode.TreeNode;
import com.google.common.collect.Lists;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/10/9
 * @since 3.0.1
 */
public class TestAnd {


    public static void main(String[] args) {
//        int[] preorder = new int[]{3, 9, 20, 15, 7};
//        int[] inorder = new int[]{9, 3, 15, 20, 7};
//        TreeNode tree = TreeNode.buildTree(preorder, inorder);
        List<Integer> list = Lists.newArrayList(1, 2, 3, null, null, 4, 5);
        TreeNode tree = TreeNode.buildTreeInPre(list, 0);
        serialize(tree);
//        System.out.println("tree = " + tree.toString());
//        preOrderTravel(tree);
        // List bfs = bfs(tree);
        //     System.out.println("bfs = " + bfs);
    }

    static List<Object> list = new ArrayList<>();

    public static String serialize(TreeNode root) {
        if (root == null) {
            return new String();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null || node.getData() == null) {
                list.add("#");
                continue;
            }
            list.add(node.getData());
            queue.add(node.getLeft());
            queue.add(node.getRight());
        }
        String ss = list.toString();
        System.out.println("ss = " + ss);
        return ss;
    }


    private static List<List<Integer>> bfs(TreeNode<Integer> tree) {
        Queue<TreeNode<Integer>> queue = new LinkedBlockingQueue<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.offer(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode<Integer> node = queue.poll();
                list.add(node.getData());
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
            }
            result.add(list);
        }
        return result;
    }

    private static void preOrderTravel(TreeNode tree) {
        if (tree == null) {
            return;
        }
        System.out.println("tree = " + tree.getData());
        preOrderTravel(tree.getLeft());
        preOrderTravel(tree.getRight());
    }
}
