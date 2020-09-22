package com.example.study.concurrent.basic.interrupt;

/**
 * @author hjs
 * @date 2020/9/21
 * @description 中断线程2种方式其一：优先抛出异常
 */
public class StopThreadWithThrowException {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true && !Thread.currentThread().isInterrupted()) {
                // 对调用方法异常使用try/catch机制处理
                try {
                    System.out.println("go");
                    aVoid();
                } catch (InterruptedException e) {
                    System.out.println("中断处理");
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    // 调用的方法，将异常传递到上层
    public static void aVoid() throws InterruptedException {
        Thread.sleep(2000);
    }
}
