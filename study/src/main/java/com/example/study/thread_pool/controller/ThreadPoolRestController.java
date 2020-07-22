package com.example.study.thread_pool.controller;

import com.example.study.thread_pool.entity.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hjs
 * @date 2020/7/16
 * @description
 */
@RestController
@RequestMapping("/rest/pools")
public class ThreadPoolRestController {
    @GetMapping
    public void threadPool() {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 10,
                        400, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(6));
//        threadPoolExecutor.setThreadFactory();
//        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 15; i++) {
            Task task = new Task(i);
            threadPoolExecutor.execute(task);
            System.out.println("核心线程池中线程数目:" + threadPoolExecutor.getCorePoolSize() +
                    ",线程池中线程数目:" + threadPoolExecutor.getPoolSize() +
                    ",队列中线程数目:" + threadPoolExecutor.getQueue().size() +
                    ",已执行线程数目:" + threadPoolExecutor.getCompletedTaskCount());
        }
//        threadPoolExecutor.shutdown();
    }
}
