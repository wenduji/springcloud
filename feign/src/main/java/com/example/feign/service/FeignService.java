package com.example.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hjs
 * @date 2020/7/29
 * @description
 */
//声明调用的微服务名称及前缀
@FeignClient(value = "study-service", path = "/rest/books")
public interface FeignService {

    @GetMapping("/lazy-load")
    void lazyLoad();
}
