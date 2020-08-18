package com.example.activiti.business.controller;

import com.example.activiti.business.service.ActivitiService;
import com.example.activiti.business.service.TestRoleService;
import com.example.common.result.ResultWrapper;
import com.example.common.utils.StringUtils;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private TestRoleService testRoleService;

    @PostMapping("/deploy/{bpmnName}")
    public ResultWrapper<Deployment> deploy(@PathVariable String bpmnName) {
        Deployment deployment = activitiService.deployProcess(bpmnName);
        return new ResultWrapper<>(deployment);
    }

    @PostMapping("/start/applicantId/{applicantId}")
    public void startProcess(@PathVariable String applicantId) {
        String processKey = "sequential";
        ProcessInstance processInstance = activitiService.startProcessByKeyAndUserId(processKey, applicantId);
        String processInstanceId = processInstance.getId();
        String taskId = activitiService.getTaskId(processInstanceId);
        activitiService.claim(taskId, applicantId);
        activitiService.completeTask(taskId);

        // 设置下级审批人
        taskId = activitiService.getTaskId(processInstanceId);
        String userId = testRoleService.getDefaultNextApprover(applicantId);
        activitiService.claim(taskId, userId);

        System.out.println("流程发起人ID：" + applicantId);
        System.out.println("流程实例ID：" + processInstanceId);
        System.out.println("下级审批人ID：" + userId);
    }

    @PostMapping("/approve-pass/approverId/{approverId}/processInstanceId/{processInstanceId}")
    public void approvePass(@PathVariable String approverId, @PathVariable String processInstanceId) {
        /*
            审批通过后，判断有没有下级审批人。
            有：流程走到下级审批人。
            无：流程结束。
        */
        String taskId = activitiService.getTaskId(processInstanceId);
        Map<String, Object> variable = new HashMap<>();
        String nextHandlerId = testRoleService.getDefaultNextApprover(approverId);
        if (StringUtils.isNotEmpty(nextHandlerId)) {
            variable.put("status", "loop");
            System.out.println("下级审批人ID：" + nextHandlerId);
        } else {
            variable.put("status", "pass");
        }
        activitiService.completeTaskWithVariable(taskId, variable);
    }

    @PostMapping("/approve-refuse/processInstanceId/{processInstanceId}")
    public void approveRefuse(@PathVariable String processInstanceId) {
        String taskId = activitiService.getTaskId(processInstanceId);
        Map<String, Object> variable = new HashMap<>();
        variable.put("status", "refuse");
        activitiService.completeTaskWithVariable(taskId, variable);
    }
}
