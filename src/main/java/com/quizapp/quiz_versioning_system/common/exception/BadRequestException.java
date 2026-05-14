package com.quizapp.quiz_versioning_system.common.exception;

import com.quizapp.quiz_versioning_system.common.constant.ErrorCodes;

public class BadRequestException extends RuntimeException {

    private final String errorCode;

    public BadRequestException(String message) {
        super(message);
        this.errorCode = ErrorCodes.BAD_REQUEST;
    }

    public String getErrorCode() {
        return errorCode;
    }
}