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

    @ApiModelProperty("节点")
    private Node node;

    @ApiModelProperty("节点Id")
    private String id;

    @ApiModelProperty("节点名称")
    private String nodeName;

    @ApiModelProperty("节点连线：从哪个节点拉出连线，值为源节点的id值")
    private String sourceRef;

    @ApiModelProperty("节点连线：指向哪个节点，值为目标节点的id值")
    private String targetRef;
}
