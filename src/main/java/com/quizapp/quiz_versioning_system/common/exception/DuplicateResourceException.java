package com.quizapp.quiz_versioning_system.common.exception;

import com.quizapp.quiz_versioning_system.common.constant.ErrorCodes;

public class DuplicateResourceException extends RuntimeException {

    private final String errorCode;

    public DuplicateResourceException(String message) {
        super(message);
        this.errorCode = ErrorCodes.DUPLICATE;
    }

    public String getErrorCode() {
        return errorCode;
    }
}