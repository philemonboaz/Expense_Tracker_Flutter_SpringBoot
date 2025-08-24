package com.example.expensetracker.dto.request_models;

public class GetAllExpenseByDateModel {

    String deviceId;
    String monthAndYear;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMonthAndYear() {
        return monthAndYear;
    }

    public void setMonthAndYear(String monthAndYear) {
        this.monthAndYear = monthAndYear;
    }
}
