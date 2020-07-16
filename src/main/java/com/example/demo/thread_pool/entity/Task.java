package com.example.demo.thread_pool.entity;

/**
 * @author hjs
 * @date 2020/7/16
 * @description
 */
public class Task implements Runnable {
    private int num;

    public Task(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行thread task..." + num);
        try {
            Thread.currentThread().sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread task..." + num + "结束");
    }
}
