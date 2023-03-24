package com.example.jdk8.jdk8demo.datastructs.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 约瑟夫问题、单向环形链表
 * </p>
 *
 * @author zhaodb 2023/3/23
 * @since 3.0.1
 */
public class Joseph {

    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
        list.showBoy();
        list.countBoy(1, 2, 5);
    }

}

class CircleSingleLinkedList {
    private Boy first = null;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println(" 至少添加一个 ");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.next = first;
                curBoy = first;
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }
    }

    public void showBoy() {
        //判断是都为空
        if (first == null) {
            System.out.println("无数据");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("boy's no %d\n", curBoy.no);
            if (curBoy.next == first) {
                break;
            }
            curBoy = curBoy.next;
        }
    }

    /**
     * 根据输入计算出圈小孩顺序
     *
     * @param startNo 从第几个开始数
     * @param countNo 数几次
     * @param nums    最初有几个
     */
    public void countBoy(int startNo, int countNo, int nums) {
        if (first == null) {
            System.out.println("无数据");
            return;
        }
        if (startNo > nums || startNo < 1) {
            System.out.println("startNo不合法");
            return;
        }
        Boy helper = first;
        //先找到最后一个节点 然后控制helper和first
        while (true) {
            if (helper.next == first) {
                System.out.println("到最后了 可以开始");
                System.out.println("helper = " + helper);
                break;
            }
            helper = helper.next;
        }
        //如果不是从第一个开始数 则需要移动Helper和first
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }
        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNo - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.next = first; //
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }

}

@Getter
@Setter
class Boy {

    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }
}