package com.example.activiti.business.entity;

import java.io.Serializable;

/**
 * task_role
 * @author 
 */
public class TaskRole implements Serializable {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 流程key
     */
    private String key;

    /**
     * 流程用户任务节点
     */
    private String taskId;

    /**
     * 当前任务处理人
     */
    private String starter;

    /**
     * 下级任务处理人
     */
    private String approver;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }
}