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


    @Modifying
    @Transactional
    @Query(value = "delete from tb_expense_data where sno =:sno and DEVICE_ID =:deviceId", nativeQuery = true)
    void deleteExpense(@Param("deviceId") String deviceId, @Param("sno") int sno);

//    @Query(value = "SELECT * FROM tb_expense_data WHERE DEVICE_ID =:deviceId OFFSET :offSetData ROWS FETCH NEXT :noOfRecords ROWS ONLY", nativeQuery = true)
//    List<ExpenseEntity> getAllExpenseDataByPagination(@Param("deviceId") String deviceId, @Param("offSetData") int offSetData, @Param("noOfRecords") int noOfRecords);

    //    @Query(value = """
//            SELECT * FROM (
//                SELECT a.*, ROWNUM rnum FROM (
//                    SELECT * FROM tb_expense_data
//                    WHERE DEVICE_ID = :deviceId
//                    ORDER BY CREATED_AT DESC
//                ) a WHERE ROWNUM <= :endRow
//            ) WHERE rnum > :startRow
//            """, nativeQuery = true)
//    List<ExpenseEntity> getAllExpenseDataByPagination(
//            @Param("deviceId") String deviceId,
//            @Param("startRow") int startRow,
//            @Param("endRow") int endRow);
    @Query(value = """
                 SELECT * FROM (
                     SELECT a.*, ROWNUM rnum FROM (
                         SELECT * FROM tb_expense_data\s
                         WHERE DEVICE_ID = :deviceId\s
                         ORDER BY CREATED_AT DESC
                     ) a\s
                     WHERE ROWNUM <= (:offset + :pageSize)
                 )\s
                 WHERE rnum > :offset
            \s""", nativeQuery = true)
    List<ExpenseEntity> getAllExpenseDataByPagination(
            @Param("deviceId") String deviceId,
            @Param("offset") int offset,
            @Param("pageSize") int pageSize
    );

//    @Query(value = "select * from tb_expense_data where DEVICE_ID =:deviceId and (CREATED_AT >= :month and CREATED_AT < :month )", nativeQuery = true)
//    List<ExpenseEntity> getAllExpenseDataByMonth(@Param("deviceId") String deviceId, @Param("month") String month);

    @Query(value = "select * from tb_expense_data where DEVICE_ID =:deviceId and (CREATED_AT >= :startOfMonth and CREATED_AT < :endOfMonth )", nativeQuery = true)
    List<ExpenseEntity> getAllExpenseDataByMonth(@Param("deviceId") String deviceId, @Param("startOfMonth") Date startOfMonth, @Param("endOfMonth") Date endOfMonth);
}
