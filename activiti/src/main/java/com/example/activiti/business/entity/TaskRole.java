package com.example.activiti.business.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRole {

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("流程key")
    private String key;

    @ApiModelProperty("流程用户任务节点")
    private String taskId;

    @ApiModelProperty("当前任务处理人")
    private String starter;

    @ApiModelProperty("下级任务处理人")
    private String approver;

}