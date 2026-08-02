package com.project.Household.ledger.mapper;

import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface LedgerMapper {
    int insertLedger (LedgerMapper ledgerMapper);

}
