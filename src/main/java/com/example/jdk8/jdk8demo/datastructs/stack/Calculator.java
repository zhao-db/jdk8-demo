package com.example.jdk8.jdk8demo.datastructs.stack;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/3/28
 * @since 3.0.1
 */
public class Calculator {

    public static void main(String[] args) {
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 opeStack = new ArrayStack2(10);
        String expression = "5+5*6-5";
        int index = 0;
        int num1;
        int num2;
        int ope;
        int res;
        while (true) {
            char ch = expression.substring(index, index + 1).charAt(0);
            if (opeStack.isOper(ch)) {
                if (opeStack.isEmpty()) {
                    opeStack.push(ch);
                } else {
                    if (opeStack.priority(ch) <= opeStack.priority(opeStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        ope = opeStack.pop();
                        res = numStack.cal(num1, num2, ope);
                        numStack.push(res);
                        opeStack.push(ch);
                    } else {
                        opeStack.push(ch);
                    }
                }
            } else {
                numStack.push(ch - 48);
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        while (true) {
            if (opeStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            ope = opeStack.pop();
            res = numStack.cal(num1, num2, ope);
            numStack.push(res);
        }
        int result = numStack.pop();
        System.out.printf("表达式 %s = %d", expression, result);

    }

}

class ArrayStack2 {

    private int maxSize;
    private int[] arrayStack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
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

    //返回运算符的优先级，优先级是程序员来确定, 优先级使用数字表示
    //数字越大，则优先级就越高.
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前的表达式只有 +, - , * , /
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;// 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
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

