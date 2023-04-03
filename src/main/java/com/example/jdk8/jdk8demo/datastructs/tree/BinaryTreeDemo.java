package com.example.jdk8.jdk8demo.datastructs.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/4/3
 * @since 3.0.1
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        tree.setRoot(root);
/*        System.out.println("前序遍历");
        tree.preOrder();
        System.out.println("中序遍历");
        tree.infixOrder();
        System.out.println("后序遍历");
        tree.postOrder();*/

        HeroNode heroNode = tree.preOrderSearch(5);
        System.out.println("heroNode = " + heroNode);
        heroNode = tree.infixOrderSearch(5);
        System.out.println("heroNode = " + heroNode);
        heroNode = tree.postOrderSearch(5);
        System.out.println("heroNode = " + heroNode);
    }

}

//定义一个二叉树
class BinaryTree {

    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }
        return null;
    }

    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        }
        return null;
    }

    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        }
        return null;
    }

}


@Getter
@Setter
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }

    //前序
    public void preOrder() {
        System.out.println(this);
        //向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序
    public void infixOrder() {
        //向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        //向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序
    public void postOrder() {
        //向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        //向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println(" 前序 ");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        //说明找到了
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        //说明找到了
        if (resNode != null) {
            return resNode;
        }
        System.out.println(" 中序 ");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        //说明找到了
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        //说明找到了
        if (resNode != null) {
            return resNode;
        }
        System.out.println(" 后序 ");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
