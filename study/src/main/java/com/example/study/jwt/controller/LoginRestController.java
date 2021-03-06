package com.example.study.jwt.controller;

import com.example.common.exception.ApiException;
import com.example.study.jwt.entity.HttpHeader;
import com.example.study.jwt.util.JWTUtils;
import com.example.common.result.ResultWrapper;
import com.example.study.test.entity.Person;
import com.example.study.test.service.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public ResultWrapper login(@RequestBody Person person) {
        return new ResultWrapper<>(personService.createJWTByPersonInfo(person));
    }

    @GetMapping("/refresh-token")
    public ResultWrapper refreshToken(HttpServletRequest httpServletRequest) {
        String refreshToken = httpServletRequest.getHeader(HttpHeader.HEADER_REFRESH_TOKEN);
        try {
            return new ResultWrapper<>(JWTUtils.getAccessTokenByRefreshToken(refreshToken));
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }
}
