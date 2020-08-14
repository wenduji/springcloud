package com.example.activiti.business.controller;

import com.example.activiti.business.service.ActivitiService;
import com.example.common.result.Result;
import com.example.common.result.ResultWrapper;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hjs
 * @date 2020/8/13
 * @description
 */
@RestController
@RequestMapping("/rest/activitis")
public class TestRestController {

    @Resource
    private ActivitiService activitiService;

    @PostMapping("/deploy/{bpmnName}")
    public ResultWrapper deploy(@PathVariable String bpmnName) {
        Deployment deployment = activitiService.deployProcess(bpmnName);
        return new ResultWrapper<>(deployment);
    }

    @PostMapping
    public Result finish() {
        String processKey = "Test5";
        String applicantId = "20";
        ProcessInstance processInstance = activitiService.startProcessByKeyAndUserId(processKey, applicantId);
        String processInstanceId = processInstance.getId();
        String taskId = activitiService.getTaskId(processInstanceId);
        activitiService.claim(taskId, "张三");
        activitiService.completeTask(taskId);
        return Result.success();
    }
}
