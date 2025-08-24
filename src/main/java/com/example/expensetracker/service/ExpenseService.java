package com.example.expensetracker.service;

import com.example.expensetracker.common.DateUtil;
import com.example.expensetracker.common.ExpenseDataHelpers;
import com.example.expensetracker.dto.AddExpenseModel;
import com.example.expensetracker.entity.ExpenseEntity;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.util.response.ApiBaseResponse;
import com.example.expensetracker.util.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Arrays;
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

    public ApiBaseResponse<String> deleteExpense(String deviceId, int sno) {
        try {
            expenseRepository.deleteExpense(deviceId, sno);
            return ResponseUtil.successResp("ok", null);
        } catch (Exception e) {

            return ResponseUtil.exceptionResp(509, "Error");
        }
    }

    public ApiBaseResponse<List<ExpenseEntity>> getAllExpenseDataByPagination(String deviceId, int pageNumber) {
        List<ExpenseEntity> data;
        try {
            int startDataRow = ExpenseDataHelpers.getStartDataRowNumber(pageNumber);
            data = expenseRepository.getAllExpenseDataByPagination(deviceId, startDataRow, 10);
            return ResponseUtil.successResp("ok", data);
        } catch (Exception e) {
            return ResponseUtil.exceptionResp(409, "Error");
        }
    }

    public ApiBaseResponse<List<ExpenseEntity>> getAllExpenseByMonth(String deviceId, String date) {
        List<ExpenseEntity> data;
        try {
            int[] splitDateArray = Arrays.stream(date.split("-")).map(String::trim).mapToInt(Integer::parseInt).toArray();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
//            Date startDateOfMonth = formatter.parse(date);
//            Date endDateOfMonth = formatter.parse(date);
//            String monthYear = date + " 00:00:00";
//            String monthYear = date;
            LocalDate parsedDate =
                    LocalDate.parse(date);

            LocalDate firstDate = YearMonth.of(parsedDate.getYear(), parsedDate.getMonth()).atDay(1);
            LocalDate lastDate = YearMonth.of(parsedDate.getYear(), parsedDate.getMonth()).atEndOfMonth();
            Date startOfMonth = Date.from(firstDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endOfMonth = Date.from(lastDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println("start of month: " + startOfMonth);
            System.out.println("end of month: " + endOfMonth);

            data = expenseRepository.getAllExpenseDataByMonth(deviceId, startOfMonth, endOfMonth);
            return ResponseUtil.successResp("ok", data);
        } catch (Exception e) {
            return ResponseUtil.exceptionResp(409, "Error");
        }
    }
}
