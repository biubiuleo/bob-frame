package com.bob.common.exception;

import com.bob.common.enums.FrameExceptionEnum;
import lombok.Data;

@Data
public class FrameException extends RuntimeException {
    private FrameExceptionEnum exceptionEnum;

    public FrameException(FrameExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
