package com.example.jdk8.jdk8demo.datastructs.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/3/28
 * @since 3.0.1
 */
public class MyLinkedListDemo {
    public static void main(String[] args) {
        MyLinkedList demo = new MyLinkedList();
        demo.addLast(1);
        demo.addLast(2);
        demo.addLast(3);
        demo.addLast(4);
        demo.addFirst(7);
        demo.list();
        System.out.println("demo.size() = " + demo.size());
        demo.addIndex(2, 888);
        demo.list();
        System.out.println("demo.size() = " + demo.size());
        demo.remove(888);
        demo.list();
        System.out.println("demo.size() = " + demo.size());

    }
}

class MyLinkedList {

    private Node head = null;
    private int size = 0;

    public void addIndex(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("add index illegal!");
        }
        if (index == 0) {
            addFirst(value);
            return;
        }
        Node prev = head;
        Node node = new Node(value);
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public void addFirst(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        //头插 先将插入节点放至head前  再将head换位
        node.next = head;
        head = node;
        size++;
    }

    public void addLast(int val) {
        Node node = new Node(val);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public void remove(int val) {
        // 1.base case 判断链表是否为空
        if (head == null) {
            return;
        }
        // 2.判断头节点就是待删除的节点
        if (head.data == val) {
            Node next = head.next;
            head.next = null;
            head = next;
            size--;
            return;
        }
        // 3.此时头节点一定不是待删除的节点,且不为空!
        Node prev = head;
        while (prev.next != null) {
            if (prev.next.data == val) {
                // 说明此时prev恰好就是待删除节点的前驱
                // cur就是待删除的节点
                Node cur = prev.next;
                prev.next = cur.next;
                cur.next = null;
                size--;
                return;
            }
            // 此时prev不是待删除节点的前驱，继续向后判断
            prev = prev.next;
        }
    }

    public int size() {
        return size;
    }

    public void list() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println("tmp = " + tmp.data);
            tmp = tmp.next;
        }
    }


}

@Getter
@Setter
class Node {

    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

}
