package com.example.activiti.business.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * multi_instances
 * @author 
 */
@Getter
@Setter
public class MultiInstances implements Serializable {

    private static final long serialVersionUID = -4571885747995582842L;

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("流程key")
    private String key;

    @ApiModelProperty("流程用户任务节点")
    private String taskId;

    @ApiModelProperty("多实例:完成条件")
    private String completeCondition;

    @ApiModelProperty("多实例:任务负责人集合")
    private String collection;

}