package com.project.Household.ledger.service;

import com.project.Household.ledger.dto.Ledger;
import com.project.Household.ledger.mapper.LedgerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LedgerService {
    private final LedgerMapper ledgerMapper;

    //저장
    public void SaveLedger(Ledger ledger){
        ledgerMapper.insertLedger(ledger);
    }

    //상세보기 조회
    public Ledger ledgerDetail(Long id){
        return ledgerMapper.ledgerDetail(id);
    }

    //조회
    public List<Ledger> ledgerList() {
        return ledgerMapper.ledgerList();
    }
}
