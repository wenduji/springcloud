package com.example.study.concurrent.basic.interrupt;

/**
 * @author hjs
 * @date 2020/9/22
 * @description interrupted()、isInterrupt()区分
 */
public class InterruptMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (; ; ) {

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.interrupt();
        System.out.println("isInterrupted... " + thread.isInterrupted());
        System.out.println("isInterrupted... " + thread.interrupted());
        System.out.println("isInterrupted... " + Thread.interrupted());
        System.out.println("isInterrupted... " + thread.isInterrupted());
    }
}
