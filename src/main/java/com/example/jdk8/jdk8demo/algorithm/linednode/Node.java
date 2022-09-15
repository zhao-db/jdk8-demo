package com.example.jdk8.jdk8demo.algorithm.linednode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/9/15
 * @since 3.0.1
 */
public class Node<T> {

    //头节点
    private Node head;
    //尾节点
    private Node last;

    private Node next;
    private T data;
    //长度
    private int size;

    public Node() {

    }

    public Node(T data) {
        this.data = data;
        head = this;
        size++;
    }

    public void insert(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node insertedNode = new Node(data);
        if (size == 0) {
            //空链表
            head = insertedNode;
            last = insertedNode;
        } else if (index == 0) {
            //头插
            insertedNode.next = head;
            head = insertedNode;
        } else if (index == size) {
            //尾插
            last.next = insertedNode;
            last = insertedNode;
        } else {
            //中间插
            Node<T> prev = getByIndex(index);
            insertedNode.next = prev.next;
            prev.next = insertedNode;
        }
        size++;
    }

    public Node<T> remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node<T> removeNode = null;
        if (index == 0) {
            //删除头
            removeNode = head;
            head = head.next;
        } else if (index == size - 1) {
            //删除尾
            Node<T> prevNode = getByIndex(index - 1);
            removeNode = prevNode.next;
            prevNode.next = null;
            last = prevNode;
        } else {
            //中间删除
            Node<T> prevNode = getByIndex(index - 1);
            Node nextNode = prevNode.next.next;
            removeNode = prevNode.next;
            prevNode.next = nextNode;
        }
        size--;
        return removeNode;
    }

    public Node<T> getByIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void output() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

}
