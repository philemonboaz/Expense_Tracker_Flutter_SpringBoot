package com.example.expensetracker.common.response;

public class ResponseUtil {
    public static <T> ApiResponse<T> successResp(String message, T data) {
        return new ApiResponse<>(200, null, message, data);
    }

    public static <T> ApiResponse<T> errorResp(String errorCode, String message) {
        return new ApiResponse<>(400, errorCode, message, null);
    }
}
