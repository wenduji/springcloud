package com.example.study.concurrent.synchronized_symbol;

/**
 * @author hjs
 * @date 2020/9/14
 * @description synchronized对象锁
 */
public class ObjectLock implements Runnable {
    static int count = 0;
    static ObjectLock instance = new ObjectLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

    @Override
    public void run() {
        // 对象锁：同步代码块形式，需要手动指定锁定对象，默认为this。
        // 也可以指定自定义的对象，如Object instance = new Object();锁住instance
        synchronized (this) {
            System.out.println("线程启动：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程结束：" + Thread.currentThread().getName());
        }

        // 对象锁的第二种形式：synchronized修饰普通方法，锁住this
        // method();
    }

    public synchronized void method() {
        System.out.println("线程启动：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程结束：" + Thread.currentThread().getName());
    }
}
