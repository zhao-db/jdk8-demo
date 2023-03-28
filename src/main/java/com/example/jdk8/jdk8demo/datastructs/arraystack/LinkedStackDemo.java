package com.example.jdk8.jdk8demo.datastructs.arraystack;

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
public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack stack = new LinkedStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.show();
        int val = stack.pop();
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("val = " + val);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        stack.show();
        val = stack.pop();
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("val = " + val);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        stack.show();
        val = stack.pop();
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("val = " + val);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        stack.show();
        val = stack.pop();
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("val = " + val);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        stack.show();
        val = stack.pop();
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("val = " + val);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        stack.show();
        val = stack.pop();
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println("val = " + val);
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        stack.show();
    }

}

class LinkedStack {

    private int size;
    /**
     * 头节点 先指向空
     */
    private Node head;

    public LinkedStack() {
        head = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void show() {
        Node tmp = head;
        while (tmp != null) {
            System.out.printf("stack=%d\n", tmp.val);
            tmp = tmp.getNext();
        }
    }

    public void push(int val) {
        Node cur = new Node(val);
        if (head != null) {
            cur.setNext(head);
        }
        head = cur;
        size++;
    }

    public int peek() {
        int res = 0;
        if (head != null) {
            res = head.val;
        }
        return res;
    }

    public int pop() {
        int res = 0;
        if (head != null) {
            res = head.val;
            head = head.getNext();
            size--;
        }
        return res;
    }

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Node {
    int val;
    private Node next;

    public Node(int val) {
        this.val = val;
    }
}