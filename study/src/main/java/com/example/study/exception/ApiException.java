package com.example.study.exception;

import com.example.study.constant.ErrorCode;
import com.example.study.result.ResultWrapper;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hjs
 * @date 2020/7/21
 * @description
 */
@Getter
@Setter
public class ApiException extends RuntimeException {
    private ResultWrapper resultWrapper = ResultWrapper.success(null);

    public ApiException(ResultWrapper resultWrapper) {
        super(resultWrapper.getMessage());
        this.resultWrapper = resultWrapper;
    }

    public ApiException(String statusCode, String message, Object data) {
        super(message);
        this.resultWrapper.setCode(statusCode);
        this.resultWrapper.setMessage(message);
        this.resultWrapper.setData(data);
    }

    public ApiException(int statusCode, String message) {
        super(message);
        this.resultWrapper.setCode(statusCode);
        this.resultWrapper.setMessage(message);
        this.resultWrapper.setData(null);
    }

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.resultWrapper.setCode(errorCode.getCode());
        this.resultWrapper.setMessage(errorCode.getMessage());
    }

    public ApiException(String message) {
        super(message);
        this.resultWrapper.setCode("1000000");
        this.resultWrapper.setMessage(message);
    }

    /**
     * Exception的异常
     * @param e
     */
    public ApiException(Exception e) {
        super(e.getMessage());
        this.resultWrapper.setMessage(e.getMessage());
    }


    public ResultWrapper getResultWrapper() {
        return resultWrapper;
    }

    public void setResultWrapper(ResultWrapper resultWrapper) {
        this.resultWrapper = resultWrapper;
    }
}
