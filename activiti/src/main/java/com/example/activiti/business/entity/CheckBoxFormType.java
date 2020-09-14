package com.example.activiti.business.entity;

import org.activiti.engine.form.AbstractFormType;

/**
 * @author hjs
 * @date 2020/9/7
 * @description
 */
public class CheckBoxFormType extends AbstractFormType {

    @Override
    public Object convertFormValueToModelValue(String propertyValue) {
        return null;
    }

    @Override
    public String convertModelValueToFormValue(Object modelValue) {
        return null;
    }

    @Override
    public String getName() {
        return "checkbox";
    }
}
