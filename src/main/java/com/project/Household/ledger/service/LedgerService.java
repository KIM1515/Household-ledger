package com.project.Household.ledger.service;

import com.project.Household.ledger.dto.Ledger;
import com.project.Household.ledger.mapper.LedgerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LedgerService {
    private final LedgerMapper ledgerMapper;

    //저장
    public void SaveLedger(Ledger ledger){
        ledgerMapper.insertLedger(ledger);
    }

    //수정
    public void updateLedger(Ledger ledger){
        ledgerMapper.updateLedger(ledger);
    }

    //삭제
    public int deleteLedger(Long id){
        return ledgerMapper.deleteLedger(id);
    }

    //상세보기 조회
    public Ledger ledgerDetail(Long id){
        return ledgerMapper.ledgerDetail(id);
    }

    //조회
    public List<Ledger> ledgerList() {
        return ledgerMapper.ledgerList();
    }

    // 기간별 목록
    public List<Ledger> ledgerListByDate(
            LocalDate startDate,
            LocalDate endDate
    ) {
        return ledgerMapper.ledgerListByDate(
                startDate,
                endDate
        );
    }

    // 카테고리별 지출 합계
    public List<Map<String, Object>> sumByCategory(
            LocalDate startDate,
            LocalDate endDate
    ) {
        return ledgerMapper.sumByCategory(
                startDate,
                endDate
        );
    }

    // 월 평균 지출
    public double avgMonthlyExpense(int months) {
        return ledgerMapper.avgMonthlyExpense(months);
    }

    public List<Map<String, Object>> expenseByDate(
            LocalDate startDate,
            LocalDate endDate) {

        return ledgerMapper.expenseByDate(startDate, endDate);
    }

    public List<Map<String, Object>> sumIncomeByDate() {
        return ledgerMapper.sumIncomeByDate();
    }

    public List<Map<String, Object>> sumExpenseByDate() {
        return ledgerMapper.sumExpenseByDate();
    }
}
