package com.project.Household.ledger.controller;

import com.project.Household.ledger.dto.Ledger;
import com.project.Household.ledger.mapper.LedgerMapper;
import com.project.Household.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    // 상세 조회
    @GetMapping("/ledger/detail")
    public String ledgerDetail(@RequestParam Long id, Model model) {

        Ledger ledger = ledgerService.ledgerDetail(id);

        model.addAttribute("ledger", ledger);

        return "detail";
    }

    // 저장
    @PostMapping("/ledger/save")
    public String saveLedger(Ledger ledger) {
        ledgerService.SaveLedger(ledger);
        return "redirect:/ledger/detail?id=" + ledger.getId();
    }

    @GetMapping("/ledger/list")
    public String ledgerList(Model model) {

        List<Ledger> ledgerList = ledgerService.ledgerList();

        model.addAttribute("ledgerList", ledgerList);

        return "list";
    }

}
