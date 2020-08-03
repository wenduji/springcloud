package com.example.study.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

@AutoConfigureMockMvc
@SpringBootTest
public class PersonTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void primaryCache() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.request(HttpMethod.GET,"/rest/person/caches/level/1")
        );
    }
}
