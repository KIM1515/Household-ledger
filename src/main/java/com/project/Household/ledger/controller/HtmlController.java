package com.project.Household.ledger.controller;

import com.project.Household.ledger.mapper.LedgerMapper;
import com.project.Household.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HtmlController {

    private final LedgerService ledgerService;

    @GetMapping("/")
    public String indexHtml(){
        return "index";
    }

    @GetMapping("/ledger")
    public String ledgerHtml(){
        return "ledger";
    }

}
