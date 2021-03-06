package com.example.study.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import java.nio.charset.Charset;

/**
 * @author hjs
 * @date 2020/7/17
 * @description
 */
@SpringBootTest
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
                        .characterEncoding("utf-8")
        ).andReturn();

        System.out.println("结果：" + mvcResult.getResponse().getContentAsString(Charset.forName("utf-8")));
    }
}
