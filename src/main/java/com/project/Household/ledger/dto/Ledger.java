package com.project.Household.ledger.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Ledger {
    private Long id;
    private String type;
    private String category;
    private Integer amount;
    private String memo;
    private LocalDate recordDate;
}
