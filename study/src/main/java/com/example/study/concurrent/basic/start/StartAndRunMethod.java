package com.example.study.concurrent.basic.start;

/**
 * @author hjs
 * @date 2020/9/15
 * @description 线程启动：start()与run()比较
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

        runnable.run();
//        new Thread(runnable).run();

        new Thread(runnable).start();
    }

}
