package com.example.activiti;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ActivitiApplicationTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    void deploy() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/activitis/deploy/test")
        );
    }

    @Test
    void finish() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/activitis")
        );
    }
}
