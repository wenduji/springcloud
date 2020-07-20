package com.example.demo.aspectJ.service;

import org.springframework.stereotype.Service;

/**
 * @author hjs
 * @date 2020/7/20
 * @description
 */
@Service
public class AspectJService {
    public void execute() {
        System.out.println("aspectJ execute ...");
    }
}
