package com.example.flowable.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hjs
 * @date 2020/8/3
 * @description flowable节点信息，前台传递关键信息，后台生成xml格式的.bpmn文件
 */
@Getter
@Setter
public class FlowableNode {

    @ApiModelProperty("节点类型")
    private String type;

    @ApiModelProperty("节点id值")
    private String id;

    @ApiModelProperty("节点连线：从哪个节点拉出连线，值为源节点的id值")
    private String sourceRef;

    @ApiModelProperty("节点连线：指向哪个节点，值为目标节点的id值")
    private String targetRef;

    @ApiModelProperty("流程是否可执行")
    private String isExecutable;

    @ApiModelProperty("描述信息")
    private String name;
}
