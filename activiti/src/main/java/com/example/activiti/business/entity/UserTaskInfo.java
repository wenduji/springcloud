package com.example.activiti.business.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * user_task_info
 * @author 
 */
@Getter
@Setter
public class UserTaskInfo implements Serializable {

    private static final long serialVersionUID = -2826747230391032216L;

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("流程key")
    private String processKey;

    @ApiModelProperty("流程用户任务节点编号")
    private String nodeId;

    @ApiModelProperty("流程用户任务节点名称")
    private String nodeName;

    @ApiModelProperty("流程用户任务节点类型（1：普通用户任务，2：多实例任务，3：组任务）")
    private Integer nodeType;

    @ApiModelProperty("任务处理人角色")
    private String handlersRole;

    @ApiModelProperty("多实例:完成条件")
    private String completeCondition;

    @ApiModelProperty("多实例:任务负责人集合")
    private String collection;

}