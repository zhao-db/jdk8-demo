package com.example.jdk8.jdk8demo.datastructs.hashtable;

import java.util.Scanner;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2023/4/3
 * @since 3.0.1
 */
public class HashTableDemo {
    public static void main(String[] args) {
       /* HashTab table = new HashTab(5);
        for (int i = 1; i < 50; i++) {
            table.add(new Emp(i, "zdb" + i));
        }
        table.list();
        Emp emp = table.get(5);
        System.out.println();
        System.out.println("emp = " + emp);*/


        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    Emp emp1 = hashTab.get(id);
                    if (emp1 != null) {
                        System.out.println("emp.name = " + emp1.name);
                    } else {
                        System.out.println("未找到");
                    }
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}

class HashTab {
    private EmpLinkedListHashTable[] array;
    private int size;

    public HashTab(int size) {
        this.size = size;
        array = new EmpLinkedListHashTable[size];
        for (int i = 0; i < size; i++) {
            array[i] = new EmpLinkedListHashTable();
        }
    }

    public Emp get(int id) {
        int arrayIndex = hashFun(id);
        EmpLinkedListHashTable listHashTable = array[arrayIndex];
        return listHashTable.get(id);
    }

    public int hashFun(int id) {
        return id % size;
    }

    public void add(Emp emp) {
        int arrayIndex = hashFun(emp.id);
        EmpLinkedListHashTable listHashTable = array[arrayIndex];
        listHashTable.add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            EmpLinkedListHashTable listHashTable = array[i];
            listHashTable.list(i + 1);
        }
    }
}

class EmpLinkedListHashTable {

    private Emp head = null;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    public Emp get(int id) {
        if (head == null) {
            return null;
        }
        Emp curEmp = head;
        boolean flag = false;
        while (true) {
            if (curEmp.id == id) {
                flag = true;
                break;
            }
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        if (flag) {
            return curEmp;
        }
        return null;
    }

    public void list(int no) {
        if (head == null) {
            System.out.printf("第 %d 条链表为空", no);
            return;
        }
        Emp curEmp = head;
        while (true) {
            System.out.printf("第 %d 条链表信息为：curEmp.id=%d, name=%s", no, curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }


}

class Emp {

    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
