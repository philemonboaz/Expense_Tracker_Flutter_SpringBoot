package com.example.expensetracker.common;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    public static Date parseStartDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date parseEndDate(String dateString) {
        LocalDate localDate = LocalDate.parse(dateString); // yyyy-MM-dd
        return Date.from(localDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
