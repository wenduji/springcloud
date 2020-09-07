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
        String bpmnFileNamePrefix = "sequential";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/test/activitis/deploy/" + bpmnFileNamePrefix)
        );
    }

    /**
     * 测试人员角色：N、A、B、C
     * 申请组成员：N、A、B
     * 审批组成员：A、B、C
     * <p>
     * 对应applicantId为 n、a、b、c
     */

    @Test
    void start() throws Exception {
        String starter = "a";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/test/activitis/start/applicantId/" + starter)
        );
    }

    @Test
    void approvePass() throws Exception {
        String approver = "c";
        String processInstanceId = "22501";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/test/activitis/approve-pass/" + approver + "/" + processInstanceId)
        );
    }

    @Test
    void approveRefuse() throws Exception {
        String processInstanceId = "";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/test/activitis/approve-refuse/" + processInstanceId)
        );
    }

    @Test
    void close() throws Exception {
        String processInstanceId = "";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/test/activitis/close/" + processInstanceId)
        );
    }

    @Test
    void deployByModel() throws Exception {
        String modelId = "5004";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/models/deployment/" + modelId)
        );
    }

    @Test
    void deleteByModel() throws Exception {
        String modelId = "67503";
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/rest/models/delete/" + modelId)
        );
    }
}
