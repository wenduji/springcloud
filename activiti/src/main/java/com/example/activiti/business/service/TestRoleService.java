package com.example.activiti.business.service;

import org.activiti.engine.delegate.DelegateTask;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hjs
 * @date 2020/8/18
 * @description
 */
@Service
public class TestRoleService {

    @Resource
    private ActivitiService activitiService;

    /**
     * 当前任务在流程中所属位置
     *
     * @param processKey      流程key
     * @param currentTaskName UserTask名称
     * @return 当前任务在流程中所属位置
     */
    public int indexInProcess(String processKey, String currentTaskName) {
        List<String> nodeList = activitiService.findProcessNodes(processKey);
        int index = nodeList.indexOf(currentTaskName);
        return index + 1;
    }

    /**
     * 分配当前任务用户组
     *
     * @param index 当前任务在流程中所属位置
     * @return 当前任务用户组
     */
    public List<String> listRolesWithCurrentNode(int index) {
        // 角色从数据库查询
        String[] roleNames = {"N", "A", "B", "C"};
        List<String> roleList = new LinkedList<>();
        if (index == 1) {
            roleList.add(roleNames[0]);
            roleList.add(roleNames[1]);
            roleList.add(roleNames[2]);
        } else {
            roleList.add(roleNames[1]);
            roleList.add(roleNames[2]);
            roleList.add(roleNames[3]);
        }
        return roleList;
    }

    /**
     * 设置任务参与人
     *
     * @param delegateTask
     * @param index        当前任务在流程中所属位置
     * @param roleList     角色
     */
    public void setUserTaskCandidateUsers(DelegateTask delegateTask, int index, List<String> roleList) {
        // Candidate Users流程变量
        String[] users = {"starters", "approvers"};
        delegateTask.setVariableLocal(users[index - 1], roleList);
    }

    /**
     * 获取当前任务负责人的下级审批人
     *
     * @param userId 当前任务负责人
     * @return 当前任务负责人的下级审批人
     */
    public String getDefaultNextApprover(String userId) {
        String[] userIds = {"n", "a", "b", "c"};
        int index = Arrays.asList(userIds).indexOf(userId);
        if (index < userIds.length - 1) {
            return userIds[index + 1];
        }
        return null;
    }
}
