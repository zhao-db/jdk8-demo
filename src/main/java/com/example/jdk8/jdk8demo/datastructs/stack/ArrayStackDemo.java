package com.example.jdk8.jdk8demo.datastructs.stack;

import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/3/28
 * @since 3.0.1
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("peek: 表示查看栈顶数据");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "peek":
                    try {
                        int res = stack.peek();
                        System.out.printf("栈顶的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}

class ArrayStack {

    private int maxSize;
    private int[] arrayStack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arrayStack = new int[this.maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int value) {
        if (isFull()) {
            return;
        }
        top++;
        arrayStack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        int value = arrayStack[top];
        top--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        return arrayStack[top];
    }

    public void list() {
        if (isEmpty()) {
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, arrayStack[i]);
        }
    }
}
