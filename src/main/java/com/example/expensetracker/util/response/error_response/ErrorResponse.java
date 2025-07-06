package com.example.expensetracker.util.response.error_response;

import static com.example.expensetracker.util.enums.ErrorCodes.getMessageByCode;

public class ErrorResponse {
    int errorCode;
    String errorMsg;

    public ErrorResponse() {
    }

    public ErrorResponse(int errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = getErrorMsg(errorCode);
    }

    String getErrorMsg(int errorCode) {
        return switch (errorCode) {
            case 400 -> getMessageByCode(400);
            case 401 -> getMessageByCode(401);
            case 404 -> getMessageByCode(404);
            case 409 -> getMessageByCode(409);
            default -> getMessageByCode(500);
        };
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
