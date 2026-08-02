package com.project.Household.ledger.service;

import com.project.Household.ledger.mapper.LedgerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LedgerService {
    private final LedgerMapper ledgerMapper;
}
