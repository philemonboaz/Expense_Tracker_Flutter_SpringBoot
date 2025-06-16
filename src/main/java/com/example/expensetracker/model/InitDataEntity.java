package com.example.expensetracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "TB_INIT_DATA")
public class InitDataEntity {

    @Id
    @Column
    String DEVICE_ID;

    @Column
    String UNIQUE_ID;

    @Column
    String JWT_TOKEN;

    public String getUniqueId() {
        return UNIQUE_ID;
    }

    public void setUniqueId(String uniqueId) {
        this.UNIQUE_ID = uniqueId;
    }

    public String getDeviceId() {
        return DEVICE_ID;
    }

    public void setDeviceId(String deviceId) {
        this.DEVICE_ID = deviceId;
    }

    public String getJwt() {
        return JWT_TOKEN;
    }

    public void setJwt(String jwt) {
        this.JWT_TOKEN = jwt;
    }


}
