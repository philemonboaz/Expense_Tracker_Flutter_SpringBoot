package com.example.expensetracker.controllers;

import com.example.expensetracker.common.constants.ErrorCodes;
import com.example.expensetracker.common.response.ApiResponse;
import com.example.expensetracker.common.response.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/preloadData/getExpense")
public class PreloadDataController {
    @GetMapping
    public ApiResponse getExpense() {
        try {


            List<Map<String, Object>> expenseList = new ArrayList<>();

            // Creating multiple sample expenses
            Map<String, Object> expense1 = new HashMap<>();
            expense1.put("id", 1);
            expense1.put("title", "Groceries");
            expense1.put("amount", 1000);
            expense1.put("createdAt", "2025-03-07");
            expense1.put("description", "Grocery shop bought");

            Map<String, Object> expense2 = new HashMap<>();
            expense2.put("id", 2);
            expense2.put("title", "Rent");
            expense2.put("amount", 5000);
            expense2.put("createdAt", "2025-03-01");
            expense2.put("description", "Monthly house rent");

            Map<String, Object> expense3 = new HashMap<>();
            expense3.put("id", 3);
            expense3.put("title", "Electricity Bill");
            expense3.put("amount", 150);
            expense3.put("createdAt", "2025-03-05");
            expense3.put("description", "Monthly electricity bill payment");

            // Add all expenses to the list
            expenseList.add(expense1);
            expenseList.add(expense2);
            expenseList.add(expense3);


            return ResponseUtil.successResp("ok", expenseList);
        } catch (Exception e) {
            ErrorCodes data = ErrorCodes.BACKEND_ERROR;
            return ResponseUtil.errorResp(data.getErrorCode(), data.getErrorMessage());
        }
    }

}
