package com.example.jdk8.jdk8demo.datastructs.linkedlist;

import lombok.Getter;

/**
 * <p>
 * 双向链表
 * </p>
 *
 * @author zhaodb 2023/3/22
 * @since 3.0.1
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //先创建几个节点
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.addByOrder(heroNode2);
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.list();
    }

}

class DoubleLinkedList {

    @Getter
    private HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 遍历双向链表
     *
     * @param
     */
    public void list() {
        if (head.next == null) {
            System.out.println(" 链表为空 ");
            return;
        }
        HeroNode2 tmp = head.next;
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
     * 双向链表添加元素到队尾
     */
    public void add(HeroNode2 heroNode) {
        HeroNode2 tmp = head;
        //遍历链表找到最后节点
        while (true) {
            if (tmp.next == null) {
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = heroNode;
        heroNode.prev = tmp;
    }

    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 tmp = head.next;
        //遍历链表找到最后节点
        boolean flag = false;
        while (true) {
            if (tmp.next == null) {
                break;
            }
            if (tmp.no == heroNode.no) {
                flag = true;
                break;
            }
            if (tmp.next.no > heroNode.no) {
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            System.out.printf(" 不能添加，信息已存在%d\t ", heroNode.no);
        } else {
            heroNode.next = tmp.next;
            heroNode.prev = tmp;
            tmp.next = heroNode;
        }
    }


    /**
     * 根据new HeroNode。no进行修改
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println(" 链表为空，不能修改 ");
            return;
        }
        HeroNode2 tmp = head.next;
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

    /**
     * 双向链表删除
     *
     * @param no
     */
    public void delete(int no) {
        HeroNode2 tmp = head.next;
        if (head.next == null) {
            System.out.println(" 为空无法删除 ");
        }
        boolean flag = false;
        while (tmp != null) {
            if (tmp.no == no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag) {
            tmp.prev.next = tmp.next;
            if (tmp.next != null) {
                tmp.next.prev = tmp.prev;
            }
        } else {
            System.out.println(" 删除节点不存在 ");
        }
    }
}

class HeroNode2 {
    public HeroNode2 next;
    public HeroNode2 prev;
    public int no;
    public String name;
    public String nickName;

    //构造器
    public HeroNode2(int no, String name, String nickname) {
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
