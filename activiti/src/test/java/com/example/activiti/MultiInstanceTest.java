package com.example.activiti;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
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
 * @date 2020/8/28
 * @description
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class MultiInstanceTest {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void deploy() throws Exception {
        String bpmnFileNamePrefix = "multi_condition";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/activitis/deploy/" + bpmnFileNamePrefix)
        );
    }

    @Test
    public void start() throws Exception {
        String starter = "b";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/activitis/start/" + starter)
        );
    }

    @Test
    public void complete() throws Exception {
        String processInstanceId = "2501";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/activitis/complete/" + processInstanceId)
        );
    }

    @Test
    public void reApply() throws Exception {
        String applicantId = "a";
        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/activitis/re-start/" + applicantId)
        );
    }

    @Test
    public void aVoid() {
        String completeCondition = "nrOfCompletedInstances/nrOfInstances > 0.6";
//        String completeCondition = "nrOfCompletedInstances>0";
        JexlContext jexlContext = new MapContext();
        jexlContext.set("nrOfCompletedInstances", 4f);
        jexlContext.set("nrOfInstances", 5);
        Expression e = new JexlEngine().createExpression(completeCondition);
        Boolean flag = (Boolean) e.evaluate(jexlContext);
        System.out.println(flag);
    }
}
