package com.example.study.aop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author hjs
 * @date 2020/7/20
 * @description Spring AOP：运行期动态代理
 */
@Aspect
@Component
public class AspectConfiguration {
    /**
     * 匹配切入点：
     * 路径下所有的aop开头的方法
     */
    public static final String POINTCUT_EXECUTION =
            "execution(* com.example.demo.*.*.*.aop*())";

    @Pointcut(POINTCUT_EXECUTION)
    public void pointCut() {
    }

    @Before(POINTCUT_EXECUTION)
    public void before() {
        System.out.println("aop before ...");
    }

    @AfterReturning(POINTCUT_EXECUTION)
    public void afterReturning() {
        System.out.println("aop afterReturning ...");
    }

    @AfterThrowing(POINTCUT_EXECUTION)
    public void afterThrowing() {
        System.out.println("aop afterThrowing ...");
    }

    @After(POINTCUT_EXECUTION)
    public void after() {
        System.out.println("aop after ...");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aop around start...");
        // 不加只会执行around通知下的方法，不会进入其他通知和匹配到的切入点
        proceedingJoinPoint.proceed();
        System.out.println("aop around end...");
    }
}
