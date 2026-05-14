package com.quizapp.quiz_versioning_system.common.exception;

import com.quizapp.quiz_versioning_system.common.constant.ErrorCodes;

public class UnauthorizedException extends RuntimeException {

    private final String errorCode;

    public UnauthorizedException(String message) {
        super(message);
        this.errorCode = ErrorCodes.UNAUTHORIZED;
    }

    public String getErrorCode() {
        return errorCode;
    }
}