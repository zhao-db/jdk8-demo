package com.example.jdk8.jdk8demo.datastructs.linkedlist;

import lombok.Data;

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
        HeroNode newHeroNode2 = new HeroNode(2, "小卢", "玉麒麟~~~");
        singleLinkedList.update(newHeroNode2);
        singleLinkedList.list();
        singleLinkedList.delete(1);
        singleLinkedList.list();
    }

}

class SingleLinkedList {
    //初始化头节点 不存放具体数据
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
