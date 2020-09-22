package com.example.study.concurrent.basic.interrupt;

/**
 * @author hjs
 * @date 2020/9/21
 * @description run()内有sleep()
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                System.out.println("run()... " + Thread.currentThread().getName());
                int num = 0;
                // !Thread.currentThread().isInterrupted()也可以不加
                // 加上是为了能快速响应中断，不需要等待代码全部执行完毕，再进入阻塞操作
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 中断异常处理
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        System.out.println("main()... " + Thread.currentThread().getName());
        thread.start();
        // 主线程阻塞500ms，让子线程有时间能执行run()，子线程再被阻塞1000ms，
        // 调用interrupt()后，抛出异常
        Thread.sleep(500);
        thread.interrupt();
    }
}
