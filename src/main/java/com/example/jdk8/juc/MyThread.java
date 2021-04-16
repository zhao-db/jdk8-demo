package com.example.jdk8.juc;

/**
 * @Description
 * @Author zhaodb
 * @Date 2020/12/15 14:50
 * @Version 1.0
 */
public class MyThread extends Thread {

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("currentThread name is : " + Thread.currentThread().getName());
        }

    }
}
