package com.project.Household.ledger.mapper;

import com.project.Household.ledger.dto.Ledger;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@MapperScan
public interface LedgerMapper {
    int insertLedger (Ledger ledger);
    int updateLedger(Ledger ledger);
    int deleteLedger(Long id);
    //Ledger ledgerDetail(Long id);
    Ledger ledgerDetail(@Param("id") Long id);
    List<Ledger> ledgerList();
    List<Map<String, Object>> sumByCategory(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<Ledger> ledgerListByDate(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    // 월 평균 지출
    double avgMonthlyExpense(@Param("months") int months);

    // 달력용 날짜별 지출 합계
    List<Map<String, Object>> expenseByDate(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    List<Map<String, Object>> sumIncomeByDate();

    List<Map<String, Object>> sumExpenseByDate();
}
