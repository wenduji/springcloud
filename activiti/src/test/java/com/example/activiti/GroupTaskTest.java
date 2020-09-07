package com.example.activiti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/8/27
 * @description
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class GroupTaskTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void deploy() throws Exception {
        String bpmnFileNamePrefix = "group";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/test/activitis/deploy/" + bpmnFileNamePrefix)
        );
    }

    @Test
    public void start() throws Exception {
        String starter = "b";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/test/activitis/start/" + starter)
        );
    }

    @Test
    public void claim() throws Exception {
        String processInstanceId = "15001";
        String userId = "role A";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/test/activitis/claim/" + processInstanceId + "/" + userId)
        );
    }

    @Test
    public void complete() throws Exception {
        String processInstanceId = "15001";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/test/activitis/complete/" + processInstanceId)
        );
    }

}
