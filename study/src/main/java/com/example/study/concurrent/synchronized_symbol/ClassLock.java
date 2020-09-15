package com.example.study.concurrent.synchronized_symbol;

/**
 * @author hjs
 * @date 2020/9/14
 * @description 类锁
 */
public class ClassLock implements Runnable {

    static ClassLock instance1 = new ClassLock();
    static ClassLock instance2 = new ClassLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        }
        System.out.println("finished...");
    }

    @Override
    public void run() {
        // 类锁：锁定静态方法形式
        // method();

        // 类锁第二种形式：同步代码块，锁定Class对象
        synchronized (ClassLock.class){
            System.out.println("线程启动：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程结束：" + Thread.currentThread().getName());
        }
    }

    public static synchronized void method() {
        System.out.println("线程启动：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程结束：" + Thread.currentThread().getName());
    }
}
