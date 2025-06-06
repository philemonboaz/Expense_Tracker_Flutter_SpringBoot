package com.example.expensetracker.common.constants;

public enum ErrorCodes {
    INTERNAL_ERROR("An unexpected error occurred"),
    VALIDATION_FAILED("Validation failed"),
    BACKEND_ERROR("Backend Error");

    private final String errorMessage;

    ErrorCodes(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return this.name();
    }

    public String getErrorMessage() {
        return errorMessage;
    }


}
