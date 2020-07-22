package com.example.study.result;

import com.example.study.constant.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author hjs
 * @date 2020/7/21
 * @description
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultWrapper<T> extends Result{
    private static final long serialVersionUID = 8227307338710971177L;

    public T data;

    /**
     * 请求成功，并带上数据
     *
     * @param data
     */
    public ResultWrapper(T data) {
        super("200", "请求成功！");
        this.data = data;
    }

    /**
     * 请求失败，并带上数据
     *
     * @param errorCode
     */
    public ResultWrapper(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
        this.data = null;
    }
    /**
     * 请求成功
     * @param data
     * @return
     */
    @JsonIgnore
    public static <T> ResultWrapper<T> success(T data) {
        ResultWrapper<T> result = new ResultWrapper<T>(data);
        return result;
    }


    /**
     * 请求失败，并带上数据
     *
     * @param errorCode
     * @param data
     */
    public ResultWrapper(ErrorCode errorCode, T data) {
        super(errorCode.getCode(), errorCode.getMessage());
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultWrapper(){

    }
}
