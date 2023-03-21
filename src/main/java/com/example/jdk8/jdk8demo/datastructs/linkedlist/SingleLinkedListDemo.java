package com.example.jdk8.jdk8demo.datastructs.linkedlist;

import lombok.Data;
import lombok.Getter;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

/**
 * <p>
 * 单向链表
 * </p>
 *
 * @author zhaodb 2023/3/20
 * @since 3.0.1
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //先创建几个节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.list();
        SingleLinkedList.reserve(singleLinkedList.getHead());
        singleLinkedList.list();
/*
        //测试一下 求单链表中有效节点的个数
        System.out.println("有效的节点个数=" + SingleLinkedList.getLength(singleLinkedList.getHead()));//2

        //测试一下看看是否得到了倒数第K个节点
        HeroNode res = SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 4);
        System.out.println("res=" + res);*/
    }

}

class SingleLinkedList {
    //初始化头节点 不存放具体数据
    @Getter
    private HeroNode head = new HeroNode(0, "", "");

    public void add(HeroNode heroNode) {
        HeroNode tmp = head;
        //遍历链表找到最后节点
        while (true) {
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode tmp = head;
        //遍历链表找到最后节点
        boolean flag = false;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            if (tmp.next.no > heroNode.no) {
                break;
            } else if (tmp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            System.out.printf(" 不能添加，信息已存在%d\t ", heroNode.no);
        } else {
            heroNode.next = tmp.next;
            tmp.next = heroNode;
        }
    }

    public void list() {
        if (head.next == null) {
            System.out.println(" 链表为空 ");
            return;
        }
        HeroNode tmp = head.next;
        while (true) {
            //是否到链表最后了
            if (tmp == null) {
                break;
            }
            System.out.println("tmp = " + tmp);
            //将tmp后移
            tmp = tmp.next;
        }
    }

    /**
     * 根据new HeroNode。no进行修改
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println(" 链表为空，不能修改 ");
        }
        HeroNode tmp = head.next;
        boolean flag = false;
        while (true) {
            if (tmp == null) {
                break;
            }
            if (tmp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.name = newHeroNode.name;
            tmp.nickName = newHeroNode.nickName;
        } else {
            System.out.println(" 未找到对应信息 ");
        }
    }

    public void delete(int no) {
        HeroNode tmp = head;
        boolean flag = false;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            if (tmp.next.no == no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.next = tmp.next.next;
        } else {
            System.out.println(" 删除节点不存在 ");
        }
    }

    //查找单链表中的倒数第k个结点 【新浪面试题】
    //思路
    //1. 编写一个方法，接收head节点，同时接收一个index
    //2. index 表示是倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    //5. 如果找到了，则返回该节点，否则返回null
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二次遍历  size-index 位置，就是我们倒数的第K个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义给辅助变量， for 循环定位到倒数的index
        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }

    //方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)

    /**
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量, 这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }


    public static void reserve(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reserve = new HeroNode(0, "", "");
        while (cur != null) {
            next = cur.next;
            cur.next = reserve.next;
            reserve.next = cur;
            cur = next;
        }
        head.next = reserve.next;

    }


    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            System.out.println(" 为空或只有一个节点，无需反转 ");
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverse = new HeroNode(0, "", "");
        while (cur != null) {
            next = cur.next;
            cur.next = reverse.next;
            reverse.next = cur;
            cur = next;
        }
        head.next = reverse.next;
    }


}

class HeroNode {

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickName = nickname;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickName + "]";
    }

}
