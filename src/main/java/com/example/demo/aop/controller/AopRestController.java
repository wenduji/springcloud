package com.example.demo.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjs
 * @date 2020/7/20
 * @description
 */
@RestController
@RequestMapping("/rest/aops")
public class AopRestController {
    @GetMapping
    public void aop(){
        System.out.println("aop execute ...");
    }

    @GetMapping("/exception")
    public void aopThrowException(){
        int result = 1 / 0;
        System.out.println("aop execute ...");
    }

    @GetMapping("/common")
    public void common(){
        System.out.println("common execute ...");
    }
}
