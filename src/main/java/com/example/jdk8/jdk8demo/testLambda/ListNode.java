package com.example.jdk8.jdk8demo.testLambda;

import lombok.Data;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Collin
 * @date 2021/6/24 11:51 上午
 */
@Data
public class ListNode<T> {

    private Node<T> first;

    public ListNode() {
        first = new Node<T>();
    }

    public void ListNodeLast(T[] data) {
        first = new Node<T>();
        Node<T> end = first;
        Node<T> s = null;
        for (T t : data) {
            s = new Node<>();
            s.setData(t);
            end.setNext(s);
            end = s;
        }
    }

    public void ListNodeFirst(T[] data) {
        // 头结点
        first = new Node<T>();
        Node<T> s = null;
        for (T t : data) {
            s = new Node<T>();
            s.setData(t);
            s.setNext(first.getNext());
            first.setNext(s);
        }
    }

    public boolean insert(T e, int index) {
        int count = 0;
        Node<T> s = first;
        Node<T> s2 = null;
        if (index < 1)
            throw new ArrayIndexOutOfBoundsException();
        while (s != null && count < index - 1) {
            s = s.getNext();
            count++;
        }

        if (s == null)
            throw new ArrayIndexOutOfBoundsException();
        else {
            s2 = new Node<T>();
            s2.setData(e);
            s2.setNext(s.getNext());
            s.setNext(s2);
        }
        return true;
    }

    public T get(int index) {

        int count = 0;
        Node<T> s = first;

        if (index < 1)
            throw new ArrayIndexOutOfBoundsException();
        while (s != null && count < index) {
            s = s.getNext();
            count++;
        }
        if (s == null)
            throw new ArrayIndexOutOfBoundsException();
        else
            return s.getData();
    }


    @Data

    public static class Node<T> {
        private T data;
        private Node<T> next;
    }


    public static void main(String[] args) {
        ListNode<Integer> list = new ListNode<>();
        Integer[] in = new Integer[]{2, 31, 346, 723, 1235, 678, 23};
        list.ListNodeFirst(in);
        System.out.println("list.toString() = " + list.toString());
        System.out.println("list.get(5) = " + list.get(5));
    }
}
