package com.example.common.constant;

import lombok.Getter;

/**
 * @author hjs
 * @date 2020/7/21
 * @description
 */
@Getter
public enum ErrorCode {
    NULL_PARAM("10000101", "请求参数为空"),
    VALUE_ERROR_PARAM("10000103", "请求参数‘%s’的值不合法"),
    NON_EXISTENT_RECORD("10000104", "请求参数‘%s’的值没有对应的数据"),

    OTHER_ERROR("00001", "其他未知错误"),
    SYSTEM_ERROR("10000", "系统异常"),
    FLOW_CREATE_ERROR("20000", "创建流程失败");

    private String code;

    private String message;

    ErrorCode(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
