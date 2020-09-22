package com.example.study.concurrent.basic.interrupt;

/**
 * @author hjs
 * @date 2020/9/21
 * @description 中断线程2种方式其一：被调用方保留中断信息，调用方恢复中断
 */
public class StopThreadWithSaveInterruptStatus implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThreadWithSaveInterruptStatus());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("中断");
                break;
            }
            aVoid();
        }
    }

    private void aVoid() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // 将中断信息传递出去
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
