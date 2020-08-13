package com.example.common.utils;

import com.example.common.constant.ErrorCode;
import com.example.common.exception.ApiException;

/**
 * @author hjs
 * @date 2020/8/13
 * @description
 */
public class Validate {

    public static void isNotNull(Object object) {
        if (object == null) {
            throw new ApiException(ErrorCode.NULL_PARAM);
        }
    }

    public static void isNotNull(Object object, ErrorCode errorCode) {
        if (object == null) {
            throw new ApiException(errorCode);
        }
    }

    public static void isNotNull(Object object, String message) {
        if (object == null) {
            throw new ApiException(message);
        }
    }

    public static void isNotEmpty(String str) {
        if (StringUtils.isEmpty(str)) {
            throw new ApiException(ErrorCode.NULL_PARAM);
        }
    }

    public static void isNotEmpty(String str, ErrorCode errorCode) {
        if (StringUtils.isEmpty(str)) {
            throw new ApiException(errorCode);
        }
    }

    public static void isNotEmpty(String str, String errorMessage) {
        if (StringUtils.isEmpty(str)) {
            throw new ApiException(errorMessage);
        }
    }

    public static void illegalParam(String varName, Object value) {
        String message = String.format(ErrorCode.VALUE_ERROR_PARAM.getMessage(), varName);
        throw new ApiException(ErrorCode.VALUE_ERROR_PARAM.getCode(), message, value);
    }

    public static void nonExistentData(String varName, Object value) {
        String message = String.format(ErrorCode.NON_EXISTENT_RECORD.getMessage(), varName);
        throw new ApiException(ErrorCode.NON_EXISTENT_RECORD.getCode(), message, value);
    }

    public static void relativeData(String varName, Object value) {
        String message = String.format(ErrorCode.NON_EXISTENT_RECORD.getMessage(), varName);
        throw new ApiException(ErrorCode.NON_EXISTENT_RECORD.getCode(), message, value);
    }
}
