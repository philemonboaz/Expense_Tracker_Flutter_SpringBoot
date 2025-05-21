package com.example.expensetracker.util.enums;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodes {
    INTERNAL_ERROR(500, "Internal Server Error"),
    UNAUTHORIZED(401, "Authentication missing - unauthorized"),
    BACKEND_ERROR(404, "Backend Error"),
    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(401, "Access denied - forbidden"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    COMMON(404, "An unexpected error occurred");


    private static final Map<Integer, ErrorCodes> CODE_MAP = new HashMap<>();

    // Static block to initialize map once
    static {
        for (ErrorCodes ec : values()) {
            CODE_MAP.put(ec.errorCode, ec);
        }
    }

    final int errorCode;
    final String errorMessage;

    ErrorCodes(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    // Fast O(1) lookup
    public static String getMessageByCode(int code) {
        ErrorCodes ec = CODE_MAP.get(code);
        return ec != null ? ec.getErrorMessage() : "Unknown error";
    }
    
    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
