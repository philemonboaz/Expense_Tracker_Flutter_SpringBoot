package com.example.expensetracker.common.response;

public class ApiResponse<T> {
    private int status;
    private String errorCode;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int status, String errorCode, String message, T data) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;

    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
