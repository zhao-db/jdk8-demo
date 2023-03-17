package com.example.jdk8.jdk8demo.datastructs.queue;

import java.util.Scanner;

/**
 * <p>
 * 环形数组队列
 * </p>
 *
 * @author zhaodb 2023/3/17
 * @since 3.0.1
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        CircleArrayQueue queue = new CircleArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看对列头");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 使用数组模拟队列
 */
class CircleArrayQueue {
    private int maxSize; //数组最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //数组存放数据

    //创建队列构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 是否空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 入队
     *
     * @param n
     */
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println(" 队列满,不能加入数据 ");
            return;
        }
        //直接将数据加入即可
        arr[rear] = n;
        //rear 后移 必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            System.out.println(" 队列空,无法取出数据 ");
            throw new RuntimeException("队列满,无法取出数据");
        }
        //这里要分析出front 是指向队列的第一个元素
        //先把front对应的值保存到临时变量
        //front后移
        int tmp = arr[front];
        front = (front + 1) % maxSize;
        return tmp;
    }

    /**
     * 显示队列
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println(" 队列空,无法取出数据 ");
        }
        //从front开始遍历 遍历
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 取出队列当前有效的数据
     *
     * @return
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            System.out.println(" 队列空,无法取出数据 ");
            throw new RuntimeException("队列满,无法取出数据");
        }
        return arr[front];
    }


}