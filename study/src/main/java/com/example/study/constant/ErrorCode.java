package com.example.study.constant;

import lombok.Getter;

/**
 * @author hjs
 * @date 2020/7/21
 * @description
 */
@Getter
public enum ErrorCode {
    OTHER_ERROR("00001", "其他未知错误"),
    SYSTEM_ERROR("10000", "系统异常");

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
