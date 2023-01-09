package com.example.jdk8.jdk8demo.threadlocal;

import com.example.jdk8.jdk8demo.threadlocal.TestThreadLocal.UserContextHolder;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaodb 2022/2/16
 * @since 3.0.1
 */
public class Service1 {
    public void process(int i) {
        User user = new User("zdb" + i);
        UserContextHolder.holder.set(user);
        Thread thread = Thread.currentThread();
        System.out.println("thread:[" + thread.getName() + "], service1:[" + user.getName() + "]");
        new Service2().process();
    }

    class Service2 {
        public void process() {
            User user = UserContextHolder.holder.get();
            Thread thread = Thread.currentThread();
            System.out.println("thread:[" + thread.getName() + "], service2:[" + user.getName() + "]");
            new Service3().process();
        }
    }

    class Service3 {
        public void process() {
            User user = UserContextHolder.holder.get();
            Thread thread = Thread.currentThread();
            System.out.println("thread:[" + thread.getName() + "], service3:[" + user.getName() + "]");
            UserContextHolder.holder.remove();
        }
    }
}
