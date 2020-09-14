package com.example.activiti.business.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.activiti.engine.form.FormType;

import java.io.Serializable;

/**
 * @author hjs
 * @date 2020/9/3
 * @description
 */
@Getter
@Setter
public class FormData implements Serializable {

    private static final long serialVersionUID = -7835643797966578661L;

    @ApiModelProperty("属性name")
    private String id;

    @ApiModelProperty("属性描述")
    private String name;

    @ApiModelProperty("属性字段类型")
    private FormType type;

    @ApiModelProperty("属性值")
    private String value;

    @ApiModelProperty("可读")
    private boolean isReadable;

    @ApiModelProperty("可编辑")
    private boolean isWritable;

    @ApiModelProperty("必填")
    private boolean isRequired;

}
