package com.example.study.thread;

/**
 * @author hjs
 * @date 2020/9/14
 * @description
 */
public class JoinTest implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("execute run()...");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new JoinTest());
        thread.start();
        thread.join();

        for (int i = 0; i < 10; i++) {
            System.out.println("execute main()...");
        }
    }
}
