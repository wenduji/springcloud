package com.example.common.exception;

import com.example.common.constant.ErrorCode;
import com.example.common.result.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author hjs
 * @date 2020/7/21
 * @description
 */
@RestControllerAdvice
@Slf4j
public class ExceptionConfig {
    @ExceptionHandler(Exception.class)
    public ResultWrapper exceptionHandler(Exception exception){
        log.error("Exception: ", exception);
        ResultWrapper resultWrapper;
        if (exception instanceof ApiException) {
            resultWrapper = ((ApiException)exception).getResultWrapper();
        } else {
            resultWrapper = new ResultWrapper(ErrorCode.OTHER_ERROR, exception);
        }
        return resultWrapper;
    }
}
