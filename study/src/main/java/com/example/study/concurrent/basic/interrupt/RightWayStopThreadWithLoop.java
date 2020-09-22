package com.example.study.concurrent.basic.interrupt;

/**
 * @author hjs
 * @date 2020/9/21
 * @description 在循环体中对线程阻塞, 不需要在循环条件中检查中断
 */
public class RightWayStopThreadWithLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                System.out.println("run()... " + Thread.currentThread().getName());
                int num = 0;
                // 不需要使用!Thread.currentThread().isInterrupted()，的原因是sleep()已经持有响应中断申请，结束当前线程的能力
                // 另一个角度：使用了!Thread.currentThread().isInterrupted()不仅是多此一举，还有可能造成程序中断，但是不抛出异常的情况。
                // 也就是同样完成了中断，却有两种表现方式（抛异常 和 不抛异常），是程序设计者不希望发生的。
                while (num <= 300) {
                    Thread.sleep(1);
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        System.out.println("main()... " + Thread.currentThread().getName());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
