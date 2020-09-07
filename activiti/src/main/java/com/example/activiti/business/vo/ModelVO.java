package com.example.activiti.business.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hjs
 * @date 2020/8/21
 * @description
 */
@Getter
@Setter
public class ModelVO {

    @ApiModelProperty("model ID")
    private String modelID;

    @ApiModelProperty("流程Key")
    private String processKey;

    @ApiModelProperty("流程名称")
    private String processName;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("流程状态")
    private String processStatus;

}
