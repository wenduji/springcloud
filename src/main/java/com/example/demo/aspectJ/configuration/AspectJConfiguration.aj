package com.example.demo.aspectJ.configuration;

/**
 * @author hjs
 * @date 2020/7/20 
 * @description AspectJ：预编译
 */
public aspect AspectJConfiguration {
    void around():call(void *.*.AspectJService.execute()){
        System.out.println("开始事务 ...");
        proceed();
        System.out.println("事务结束 ...");
    }
}
