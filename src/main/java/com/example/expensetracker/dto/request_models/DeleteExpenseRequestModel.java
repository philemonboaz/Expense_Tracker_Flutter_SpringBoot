package com.example.expensetracker.dto.request_models;

public class DeleteExpenseRequestModel {
    String deviceId;
    int sno;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }
}
