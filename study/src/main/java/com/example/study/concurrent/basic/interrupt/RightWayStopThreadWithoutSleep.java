package com.example.study.concurrent.basic.interrupt;

/**
 * @author hjs
 * @date 2020/9/21
 * @description 通常情况下，run()内没有sleep()和wait()的情况下停止线程
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的整数");
            }
            num++;
        }
        System.out.println("执行完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
