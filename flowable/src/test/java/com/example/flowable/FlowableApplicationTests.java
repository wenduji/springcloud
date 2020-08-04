package com.example.flowable;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class FlowableApplicationTests {
    @Resource
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        String jsonStr = "[\n" +
                "  {\n" +
                "    \"type\":\"process\",\n" +
                "    \"id\":\"Test\",\n" +
                "    \"isExecutable\":\"true\",\n" +
                "    \"name\":\"测试\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"type\":\"startEvent\",\n" +
                "    \"id\":\"start\"  \n" +
                "  },\n" +
                "  {\n" +
                "    \"type\":\"endEvent\",\n" +
                "    \"id\":\"end\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"type\":\"sequenceFlow\",\n" +
                "    \"id\":\"flow\",\n" +
                "    \"sourceRef\":\"start\",\n" +
                "    \"targetRef\":\"end\"\n" +
                "  }\n" +
                "]";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/flow-creations")
                        .content(jsonStr)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );
    }

}
