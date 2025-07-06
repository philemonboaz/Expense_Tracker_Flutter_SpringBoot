package com.example.expensetracker.service;

import com.example.expensetracker.common.DateUtil;
import com.example.expensetracker.dto.AddExpenseModel;
import com.example.expensetracker.entity.ExpenseEntity;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.util.response.ApiBaseResponse;
import com.example.expensetracker.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public ApiBaseResponse<String> addExpense(AddExpenseModel data) {
        try {
            expenseRepository.addExpenseData(data.getDeviceId(), data.getTitle(), data.getAmount(), new Date(), data.getDescription());
            return ResponseUtil.successResp("oK", null);
        } catch (Exception e) {
            System.out.println("Exception " + e);
            return ResponseUtil.exceptionResp(409, "error");
        }
    }

    public ApiBaseResponse<List<ExpenseEntity>> getAllExpenseData(String deviceId) {
        List<ExpenseEntity> data;
        try {
            data = expenseRepository.getAllExpenseData(deviceId);
            return ResponseUtil.successResp("ok", data);
        } catch (Exception e) {
            System.out.println("Exception " + e);
            return ResponseUtil.exceptionResp(409, "error");
        }
    }

    public ApiBaseResponse<List<ExpenseEntity>> getAllExpenseData(String deviceId, String fromDateStr, String toDateStr) {
        List<ExpenseEntity> data;
        try {
            Date fromDate = DateUtil.parseStartDate(fromDateStr);
            Date toDate = DateUtil.parseEndDate(toDateStr);
            data = expenseRepository.getExpenseDataWithinSomeDateRange(deviceId, fromDate, toDate);
            return ResponseUtil.successResp("ok", data);
        } catch (Exception e) {
            System.out.println("Exception " + e);
            return ResponseUtil.exceptionResp(409, "Error");
        }
    }
}
