package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/7/16
 * @description
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BookTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void testLazyLoad() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.request(HttpMethod.GET,"/rest/books/lazy-load")
        );
    }
}
