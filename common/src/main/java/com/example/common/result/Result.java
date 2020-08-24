package com.example.common.result;

import com.example.common.constant.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author hjs
 * @date 2020/7/21
 * @description
 */
@Getter
@Setter
public class Result implements Serializable {

    private static final long serialVersionUID = -923442879040743200L;

    private String statusCode;

    private String message;

    public void setCode(String code) {
        this.statusCode = code;
    }

    public void setCode(int code) {
        this.statusCode = String.valueOf(code);
    }

    public Result(){

    }

    public Result(String statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;
    }

    @JsonIgnore
    public static Result success() {
        Result result = new Result("200", "请求成功！");
        return result;
    }

    @JsonIgnore
    public static Result error(ErrorCode errorCode) {
        Result result = new Result(errorCode.getCode(), errorCode.getMessage());
        return result;
    }

    public boolean isSuccess() {
        return "200".equals(this.getStatusCode());
    }
}
