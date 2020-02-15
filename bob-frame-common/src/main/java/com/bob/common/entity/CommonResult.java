package com.bob.common.entity;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName CommonResult
 * @Description The common result for api operation
 * @Author bob
 * @Date 2018/7/12 14:44
 * @Version 1.0
 */
@Data
public class CommonResult<T> extends BaseBean{

    private String code;
    private String msg;
    private T data;

    public static final class Builder{
        private String code;
        private String msg;
        private Object data;

        public Builder code(String code){
            this.code = code;
            return this;
        }

        public Builder message(String message){
            this.msg = message;
            return this;
        }

        public Builder data(Object data){
            this.data = data;
            return this;
        }

        public CommonResult build(){
            return new CommonResult(this);
        }
    }

    private CommonResult(Builder builder){
        this.code = builder.code;
        this.msg = builder.msg;
        this.data = (T) builder.data;
    }

    public CommonResult(){

    }

    public static Builder builder(){
        return new Builder();
    }


    public static CommonResult error(Exception exception){
        return CommonResult.builder()
                .code(String.valueOf(false))
                .message(exception.getMessage())
                .build();
    }

    public static <M> CommonResult<M> error(Exception exception, M data){
        return CommonResult.builder()
                .code(String.valueOf(false))
                .message(exception.getMessage())
                .data(data)
                .build();
    }

    public static CommonResult error(String message){
        return CommonResult.builder()
                .code(String.valueOf(false))
                .message(message)
                .build();
    }


    public static <M> CommonResult<M> custom(String code, String message , M data){
        return CommonResult.builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <M> CommonResult<M> custom(Map.Entry<String, String> entry, M data){
        return CommonResult.builder()
                .code(entry.getKey())
                .message(entry.getValue())
                .data(data)
                .build();
    }

    public static CommonResult custom(String code, String message){
        return custom(code, message, null);
    }

    public static CommonResult custom(Map.Entry<String, String> entry){
        return custom(entry, null);
    }


    public static <M> CommonResult<M> success(String message, M data){
        return CommonResult.builder()
                .code(String.valueOf(true))
                .message(message)
                .data(data)
                .build();
    }

    public static <M> CommonResult<M> success(M data){
        return CommonResult.builder()
                .code(String.valueOf(true))
                .data(data)
                .build();
    }

}
