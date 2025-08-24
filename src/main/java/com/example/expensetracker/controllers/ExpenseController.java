package com.example.expensetracker.controllers;

import com.example.expensetracker.dto.AddExpenseModel;
import com.example.expensetracker.dto.request_models.DeleteExpenseRequestModel;
import com.example.expensetracker.dto.request_models.GetAllExpenseByDateModel;
import com.example.expensetracker.dto.request_models.GetAllExpensePaginationRequestModel;
import com.example.expensetracker.dto.request_models.GetAllExpenseRequestModel;
import com.example.expensetracker.entity.ExpenseEntity;
import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.util.response.ApiBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/addExpense")
    public ApiBaseResponse<String> addExpense(@RequestBody AddExpenseModel data) {
        return expenseService.addExpense(data);
    }

    @PostMapping("/getAllExpense")
    public ApiBaseResponse<List<ExpenseEntity>> getAllExpense(@RequestBody GetAllExpenseRequestModel requestBody) {
        if (requestBody.getFromDate() == null || requestBody.getToDate() == null) {
            return expenseService.getAllExpenseData(requestBody.getDeviceId());
        } else {
            return expenseService.getAllExpenseData(requestBody.getDeviceId(), requestBody.getFromDate(), requestBody.getToDate());
        }
    }

    @PostMapping("/deleteExpense")
    public ApiBaseResponse<String> deleteExpense(@RequestBody DeleteExpenseRequestModel requestBody) {
        return expenseService.deleteExpense(requestBody.getDeviceId(), requestBody.getSno());
    }

    @PostMapping("/getAllExpensePagination")
    public ApiBaseResponse<List<ExpenseEntity>> getAllExpensePagination(@RequestBody GetAllExpensePaginationRequestModel requestBody) {
        return expenseService.getAllExpenseDataByPagination(requestBody.getDeviceId(), requestBody.getPageNumber());
    }

    @PostMapping("/getExpenseByDate")
    public ApiBaseResponse<List<ExpenseEntity>> getAllExpenseByMonthAndYear(@RequestBody GetAllExpenseByDateModel requestBody) {
        return expenseService.getAllExpenseByMonth(requestBody.getDeviceId(), requestBody.getMonthAndYear());
    }


}
