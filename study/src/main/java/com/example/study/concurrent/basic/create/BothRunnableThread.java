package com.example.study.concurrent.basic.create;

/**
 * @author hjs
 * @date 2020/9/15
 * @description 同时使用Thread和Runnable
 */
public class BothRunnableThread {
    public static void main(String[] args) {
        // 第一个@Override:实现Runnable的run()
        // 第二个@Override:重写Thread的run()
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable...");
            }
        }) {
            @Override
            public void run() {
                System.out.println("Thread...");
            }
        }.start();
    }
}
