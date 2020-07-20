package com.example.demo.aspectJ.controller;

import com.example.demo.aspectJ.service.AspectJService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/7/20
 * @description
 */
@RestController
@RequestMapping("/rest/aspectJ")
public class AspectJRestController {
    @Resource
    private AspectJService aspectJService;

    @GetMapping
    public void aspectJ(){
        aspectJService.execute();
    }
}
