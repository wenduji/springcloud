package com.example.activiti.business.vo;

import com.example.activiti.business.entity.FormData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author hjs
 * @date 2020/9/3
 * @description
 */
@Getter
@Setter
public class FormDataVO extends FormData {

    @ApiModelProperty("下拉框属性值")
    private Map<String, Object> optionsMap;

}
