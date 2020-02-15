package com.bob.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    public static final String SUCCESS_CODE = "1000";

    private String code;
    private String message;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static Result success(Object data) {
        Result result = Result.success();
        result.setData(data);
        return result;
    }

    public static Result failure(String code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result failure(String code, String message, Object data) {
        Result result = Result.failure(code, message);
        result.setData(data);
        return result;
    }
}
