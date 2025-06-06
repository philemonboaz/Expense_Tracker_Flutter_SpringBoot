package com.example.expensetracker.util.response;

public class ResponseUtil {
    public static <T> ApiBaseResponse<T> successResp(String message, T data) {
        return new ApiBaseResponse<>(200, message, data);
    }

    public static <T> ApiBaseResponse<T> errorResp(int errorCode, String message) {
        return new ApiBaseResponse<>(errorCode, message, null);
    }

    public static <T> ApiBaseResponse<T> exceptionResp(int errorCode, String message) {
        return new ApiBaseResponse<>(errorCode, message, null);
    }

}
