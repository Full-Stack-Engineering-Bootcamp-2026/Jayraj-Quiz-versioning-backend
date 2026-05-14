package com.quizapp.quiz_versioning_system.common.exception;

import com.quizapp.quiz_versioning_system.common.constant.ErrorCodes;

public class ResourceNotFoundException extends RuntimeException {

    private final String errorCode;

    public ResourceNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCodes.NOT_FOUND;
    }

    public String getErrorCode() {
        return errorCode;
    }
}