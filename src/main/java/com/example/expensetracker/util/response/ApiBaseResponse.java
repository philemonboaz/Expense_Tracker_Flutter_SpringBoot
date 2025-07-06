package com.example.expensetracker.util.response;

import com.example.expensetracker.util.enums.Status;
import com.example.expensetracker.util.response.error_response.ErrorResponse;

import java.util.Set;

public class ApiBaseResponse<T> {
    private static final Set<Integer> clientErrors = Set.of(400, 401, 403, 404, 500, 501, 502);
    private static final Set<Integer> codeErrors = Set.of(409);
    private int statusCode;
    private ErrorResponse error;
    private String message;
    private String status;
    private T data;


    public ApiBaseResponse() {
    }

    public ApiBaseResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.status = getStatusFromCode(statusCode);
        this.error = (status.equalsIgnoreCase(Status.Error.toString())) ? new ErrorResponse(statusCode) : null;
        this.message = message;
        this.data = data;
    }

    String getStatusFromCode(int statusCode) {
        if (statusCode == 200) return Status.Success.toString();
        else if (clientErrors.contains(statusCode)) return Status.Error.toString();
        else if (codeErrors.contains(statusCode)) return Status.Error.toString();
        return Status.Success.toString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}

