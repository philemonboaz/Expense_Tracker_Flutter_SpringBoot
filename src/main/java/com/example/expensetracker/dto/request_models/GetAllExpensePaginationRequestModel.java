package com.example.expensetracker.dto.request_models;

public class GetAllExpensePaginationRequestModel {
    String deviceId;
    int pageNumber;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
