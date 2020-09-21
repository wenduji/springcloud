package com.example.study.concurrent.basic.create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author hjs
 * @date 2020/9/15
 * @description 实现Callable接口，本质还是new Thread(Runnable target)
 */
public class CallableThread implements Callable {
    public static void main(String[] args) {
        new Thread(new FutureTask<>(new CallableThread())).start();
    }

    @Override
    public Object call() {
        System.out.println("call()...");
        return null;
    }
}
