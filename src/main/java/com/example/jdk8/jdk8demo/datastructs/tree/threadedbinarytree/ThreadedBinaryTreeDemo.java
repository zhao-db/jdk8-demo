package com.example.jdk8.jdk8demo.datastructs.tree.threadedbinarytree;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 线索化二叉树
 * </p>
 *
 * @author zhaodb 2023/4/4
 * @since 3.0.1
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        tree.setRoot(root);
        tree.threadedNodes(root);

        //测试: 以10号节点测试
//        HeroNode leftNode = node5.getLeft();
//        HeroNode rightNode = node5.getRight();
//        System.out.println("10号结点的前驱结点是 =" + leftNode); //3
//        System.out.println("10号结点的后继结点是=" + rightNode); //1

        tree.threadedInfixOrder();

    }
}

@Getter
@Setter
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    // leftType=0 表示指向的是左子树 =1 表示指向的是前驱节点
    private int leftType;
    // rightType=0 表示指向的是右子树 =1 表示指向的是后继节点
    private int rightType;

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

    /**
     * 简单版 找到节点后直接删除 不管其是否含有左右子树
     *
     * @param no
     */
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    /**
     * 1、叶子节点直接删除
     * 2、非叶子节点判断 有几个子节点
     * 有两个子节点的话 左子节点替换被删除节点 只有一个子节点的话直接替换
     *
     * @param no
     */
    public void delNodeAndLeftUp(int no) {
        //左子节点不为空并且左子节点的值是要删除的值
        if (this.left != null && this.left.no == no) {
            //需要判断要删除的左子节点 有没有子节点 如果还有子节点 则提升左子节点
            if (this.left.left != null && this.left.right != null) {
                this.left.left.right = this.left.right;
                this.left = this.left.left;
                return;
            }
            if (this.left.left == null && this.left.right != null) {
                this.left = this.left.right;
                return;
            }
            this.left = null;
        }
        //右子节点不为空并且右子节点的值是要删除的值
        if (this.right != null && this.right.no == no) {
            //需要判断要删除的右子节点 有没有子节点 如果还有子节点 则提升左子节点
            if (this.right.left != null && this.right.right != null) {
                this.right.left.right = this.right.right;
                this.right = this.right.left;
                return;
            }
            if (this.right.left == null && this.right.right != null) {
                this.right = this.right.right;
                return;
            }
            this.right = null;
        }
        if (this.left != null) {
            this.left.delNodeAndLeftUp(no);
        }
        if (this.right != null) {
            this.right.delNodeAndLeftUp(no);
        }
    }

}

//定义一个二叉树
class ThreadedBinaryTree {

    private HeroNode root;
    //递归进行线索化时 pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //中序线索化的方法
    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        threadedNodes(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        threadedNodes(node.getRight());
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

    public void delNode(int no) {
        if (root == null) {
            return;
        }
        if (root.getNo() == no) {
            this.root = null;
        }
        root.delNode(no);
    }

    public void delNodeAndLeftUp(int no) {
        if (root == null) {
            return;
        }
        if (root.getNo() == no) {
            //需要判断要删除的左子节点 有没有子节点 如果还有子节点 则提升左子节点
            if (root.getLeft() != null && root.getRight() != null) {
                root.getLeft().setRight(root.getRight());
                root = root.getLeft();
                return;
            }
            if (root.getLeft() == null && root.getRight() != null) {
                root = root.getRight();
                return;
            }
            //无子树 直接删除
            root = null;
        }
        root.delNodeAndLeftUp(no);
    }

    /**
     * 遍历中序线索化二叉树
     */
    public void threadedInfixOrder() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

}
