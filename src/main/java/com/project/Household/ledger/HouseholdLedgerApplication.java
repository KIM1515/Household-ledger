package com.project.Household.ledger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.project.Household.ledger.mapper")
@SpringBootApplication
public class HouseholdLedgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseholdLedgerApplication.class, args);
	}
}
