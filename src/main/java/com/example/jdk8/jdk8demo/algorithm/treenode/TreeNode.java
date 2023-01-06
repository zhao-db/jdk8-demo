package com.example.jdk8.jdk8demo.algorithm.treenode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import com.google.common.collect.Lists;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/9/16
 * @since 3.0.1
 */
@Slf4j
@Data
public final class TreeNode<T> {

    private T data;

    private TreeNode left;

    private TreeNode right;

    public TreeNode() {

    }

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> preList = new ArrayList<>(preorder.length);
        List<Integer> inList = new ArrayList<>(inorder.length);
        for (int i = 0; i < preorder.length; i++) {
            preList.add(preorder[i]);
            inList.add(inorder[i]);
        }
        return buildTreeInPre(preList, inList);
    }

    public static TreeNode buildTreeInPre(List<Integer> treeOrder) {
        if (treeOrder.size() == 0 || treeOrder.get(0) == null) {
            treeOrder.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(treeOrder.remove(0));
        root.left = buildTreeInPre(treeOrder);
        root.right = buildTreeInPre(treeOrder);
        return root;
    }


    public static TreeNode buildTreeInPre(List<Integer> treeOrder, int index) {
        if (treeOrder.size() <= index) {
            return null;
        }
        TreeNode root = new TreeNode(treeOrder.get(index));
        root.left = buildTreeInPre(treeOrder, 2 * index + 1);
        root.right = buildTreeInPre(treeOrder, 2 * index + 2);
        return root;
    }

    private static TreeNode buildTreeInPre(List<Integer> preList, List<Integer> inList) {
        if (inList.size() == 0) {
            return null;
        }
        int rootVal = preList.remove(0);
        TreeNode root = new TreeNode(rootVal);
        int midIdx = inList.indexOf(rootVal);
        root.left = buildTreeInPre(preList, inList.subList(0, midIdx));
        root.right = buildTreeInPre(preList, inList.subList(midIdx + 1, inList.size()));
        return root;
    }

    public TreeNode createTreeNodeByArray(T[] data, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(data[mid]);
        root.left = createTreeNodeByArray(data, left, mid - 1);
        root.right = createTreeNodeByArray(data, mid + 1, right);
        return root;
    }

    public TreeNode createTreeNodeByLinkedList(LinkedList linkedList) {

        return null;
    }

    public void idOrderTraveral(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            preOrderTravel(node.getLeft());
        }
        log.info("node.data:{} ", node.data);
        if (node.right != null) {
            preOrderTravel(node.getRight());
        }
    }

    public void preOrderTravel(TreeNode node) {
        if (node == null) {
            return;
        }
        log.info("node.data:{} ", node.data);
        if (node.left != null) {
            preOrderTravel(node.getLeft());
        }
        if (node.right != null) {
            preOrderTravel(node.getRight());
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(1, 2, 3, null, null, 4, 5);
        TreeNode node = TreeNode.buildTreeInPre(list);
        System.out.println("node = " + node.toString());
        node.preOrderTravel(node);
    }

//    public static void main(String[] args) {
//        TreeNode<Integer> treeNode = new TreeNode<>();
//        Integer[] data = {1, 2, 3, 23, 36, 45, 412, 512, 521, 666};
//        TreeNode node = treeNode.createTreeNodeByArray(data, 0, data.length - 1);
//        node.idOrderTraveral(node);
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        node.prevOrderTraveral(node);
//    }

}
