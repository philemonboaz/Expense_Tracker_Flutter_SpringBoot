package com.example.expensetracker.common;

public class ExpenseDataHelpers {
    public static int getStartDataRowNumber(int pageNumber) {
        return (pageNumber * 10) - 10;
    }
}
