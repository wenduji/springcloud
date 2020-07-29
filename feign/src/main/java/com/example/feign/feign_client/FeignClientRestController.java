package com.example.feign.feign_client;

import com.example.feign.service.FeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/7/29
 * @description
 */
@RestController
@RequestMapping("/feign-clients")
public class FeignClientRestController {

    @Resource
    private FeignService feignService;

    @GetMapping
    public void feignClient() {
        feignService.lazyLoad();
    }
}
