package com.example.study.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/7/17
 * @description
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class JWTTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void login() throws Exception {
        String person = "{\n" +
                        "   \"id\": \"2\"" +
                        "}";
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType("application/json")
                        .content(person)
        ).andReturn();

        System.out.println("结果：" + mvcResult.getResponse().getContentAsString());
    }
}
