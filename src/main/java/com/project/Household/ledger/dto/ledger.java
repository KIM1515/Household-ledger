package com.project.Household.ledger.dto;

import lombok.Data;

@Data
public class ledger {
    private String id;
    private String type;
    private String category;
    private double amount;
    private String memo;
    private String record_date;
}
