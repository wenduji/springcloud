package com.example.demo.aspectJ;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
public class AspectJTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void aspectJ() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/rest/aspectJ")
        );
    }
}
