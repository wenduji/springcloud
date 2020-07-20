package com.example.demo.aop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/7/20
 * @description
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AopTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void aop() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/rest/aops")
        );
    }

    @Test
    public void aopThrowException() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/rest/aops/exception")
        );
    }

    @Test
    public void common() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/rest/aops/common")
        );
    }
}
