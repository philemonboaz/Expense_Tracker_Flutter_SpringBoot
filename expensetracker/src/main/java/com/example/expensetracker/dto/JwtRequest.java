package com.example.expensetracker.dto;

import org.springframework.stereotype.Component;

@Component
public class JwtRequest {
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}
