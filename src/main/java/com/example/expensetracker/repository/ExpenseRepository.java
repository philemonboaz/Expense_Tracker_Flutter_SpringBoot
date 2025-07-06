package com.example.expensetracker.repository;

import com.example.expensetracker.entity.ExpenseEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into tb_expense_data (DEVICE_ID, TITLE, AMOUNT, CREATED_AT, DESCRIPTION) values(:deviceId, :title, :amount, :createdAt, :description)", nativeQuery = true)
    void addExpenseData(@Param("deviceId") String deviceId,
                        @Param("title") String title,
                        @Param("amount") String amount,
                        @Param("createdAt") Date createdAt,
                        @Param("description") String description);

    @Query(value = "select * from tb_expense_data where DEVICE_ID =:deviceId", nativeQuery = true)
    List<ExpenseEntity> getAllExpenseData(@Param("deviceId") String deviceId);

    @Query(value = "select * from tb_expense_data where DEVICE_ID =:deviceId and (CREATED_AT >= :fromDate and CREATED_AT < :toDate )", nativeQuery = true)
    List<ExpenseEntity> getExpenseDataWithinSomeDateRange(@Param("deviceId") String deviceId,
                                                          @Param("fromDate") Date fromDate,
                                                          @Param("toDate") Date toDate);

}
