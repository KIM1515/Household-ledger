package com.project.Household.ledger.mapper;

import com.project.Household.ledger.dto.Ledger;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface LedgerMapper {
    int insertLedger (Ledger ledger);
    //Ledger ledgerDetail(Long id);
    Ledger ledgerDetail(@Param("id") Long id);
    List<Ledger> ledgerList();
}
