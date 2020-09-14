package com.example.activiti.business.controller;

import com.example.activiti.business.service.ActivitiService;
import com.example.activiti.business.vo.FormDataVO;
import com.example.common.result.ResultWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.FormType;
import org.activiti.engine.impl.form.EnumFormType;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author hjs
 * @date 2020/9/3
 * @description
 */
@RestController
@RequestMapping("/rest/flows")
@Api("流程操作接口")
public class ActivitiRestController {

    @Resource
    private ActivitiService activitiService;

    @ApiOperation("流程表单信息")
    @GetMapping("/form-data/{processKey}")
    public ResultWrapper getProcessFormData(@PathVariable String processKey) {
        List<FormProperty> formPropertyList = activitiService.getProcessFormData(processKey);
        List<FormDataVO> formDataVOList = new ArrayList<>(formPropertyList.size());
        FormProperty formProperty;
        FormDataVO formDataVO;
        FormType formType;
        for (int i = 0, size = formPropertyList.size(); i < size; i++) {
            formProperty = formPropertyList.get(i);
            formDataVO = new FormDataVO();
            BeanUtils.copyProperties(formProperty, formDataVO);

            formType = formProperty.getType();
            if (formType instanceof EnumFormType) {
                LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) formType.getInformation("values");
                formDataVO.setOptionsMap(map);
            }
            formDataVOList.add(formDataVO);
        }
        return new ResultWrapper(formDataVOList);
    }

    @ApiOperation("启动流程")
    public ResultWrapper startProcess() {
        return null;
    }

}
