package com.example.demo.jwt.controller;

import com.example.demo.jwt.entity.HttpHeader;
import com.example.demo.jwt.util.JWTUtils;
import com.example.demo.test.entity.Person;
import com.example.demo.test.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hjs
 * @date 2020/7/17
 * @description
 */
@RestController
@RequestMapping("/login")
public class LoginRestController {
    @Resource
    private PersonService personService;

    @PostMapping
    public Map<String, String> login(@RequestBody Person person) {
        return personService.createJWTByPersonInfo(person);
    }

    @GetMapping("/refresh-token")
    public String refreshToken(HttpServletRequest httpServletRequest) {
        String refreshToken = httpServletRequest.getHeader(HttpHeader.HEADER_REFRESH_TOKEN);
        try {
            return JWTUtils.getAccessTokenByRefreshToken(refreshToken);
        } catch (Exception e) {
            // 解析失败，抛出异常
            throw e;
        }
    }
}
