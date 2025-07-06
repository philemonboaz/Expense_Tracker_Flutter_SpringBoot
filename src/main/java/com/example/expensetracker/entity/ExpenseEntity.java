package com.example.expensetracker.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_expense_data")
public class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_gen_seq")
    @SequenceGenerator(name = "expense_gen_seq", sequenceName = "expense_seq", allocationSize = 1)
    @Column(name = "SNO")
    private Long SNO;
    @Column(name = "DEVICE_ID")
    private String deviceId;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AMOUNT")
    private String amount;
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public String toString() {
        return "ExpenseEntity{" +
                "SNO=" + SNO +
                ", deviceId='" + deviceId + '\'' +
                ", title='" + title + '\'' +
                ", amount='" + amount + '\'' +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseEntity that = (ExpenseEntity) o;
        return Objects.equals(SNO, that.SNO) && Objects.equals(deviceId, that.deviceId) && Objects.equals(title, that.title) && Objects.equals(amount, that.amount) && Objects.equals(createdAt, that.createdAt) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SNO, deviceId, title, amount, createdAt, description);
    }

    public Long getSNO() {
        return SNO;
    }

    public void setSNO(Long SNO) {
        this.SNO = SNO;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
