package com.bob.common.handler;

import com.bob.common.entity.CommonResult;
import com.bob.common.exception.FrameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class FrameExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception exception) {
        return CommonResult.error(exception);
    }

    @ExceptionHandler(FrameException.class)
    public CommonResult handleInterceptorException(FrameException e) {
        return CommonResult.custom(e.getExceptionEnum().getCode(), e.getExceptionEnum().getMessage());
    }
}
