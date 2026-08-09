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

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HtmlController {

    private final LedgerService ledgerService;

    @GetMapping("/")
    public String indexHtml(Model model){
        List<Map<String, Object>> incomeList =
                ledgerService.sumIncomeByDate();

        List<Map<String, Object>> expenseList =
                ledgerService.sumExpenseByDate();

        model.addAttribute("incomeList", incomeList);
        model.addAttribute("expenseList", expenseList);

        return "index";
    }

    @GetMapping("/ledger")
    public String ledgerHtml(
            @RequestParam(required = false) String recordDate,
            Model model) {

        if (recordDate != null) {
            model.addAttribute("selectedDate", recordDate);
        }

        return "ledger";
    }

//    @GetMapping("/ledger")
//    public String ledgerHtml(){
//        return "ledger";
//    }

    // 상세 조회
    @GetMapping("/ledger/detail")
    public String ledgerDetail(@RequestParam(required = false) Long id, Model model) {

        if (id == null) {
            return "redirect:/ledger/list";
        }

        Ledger ledger = ledgerService.ledgerDetail(id);

        // 존재하지 않는 id인 경우
        if (ledger == null) {
            return "redirect:/ledger/list";
        }

        model.addAttribute("ledger", ledger);

        return "detail";
    }

    // 저장
    @PostMapping("/ledger/save")
    public String saveLedger(Ledger ledger) {
        ledgerService.SaveLedger(ledger);
        return "redirect:/ledger/detail?id=" + ledger.getId();
    }

    @GetMapping("/ledger/listall")
    public String ledgerListAll(Model model) {

        List<Ledger> ledgerList = ledgerService.ledgerList();

        model.addAttribute("ledgerList", ledgerList);
        model.addAttribute("all", true);

        return "list";
    }

    //수정
    @GetMapping("/ledger/edit")
    public String edit(@RequestParam(required = false) Long id, Model model){

        if (id == null) {
            return "redirect:/ledger/list";
        }

        Ledger ledger = ledgerService.ledgerDetail(id);

        // 존재하지 않는 id인 경우
        if (ledger == null) {
            return "redirect:/ledger/list";
        }

        model.addAttribute("ledger", ledger);

        return "edit";
    }

    @PostMapping("/ledger/update")
    public String updateLedger(Ledger ledger){

        ledgerService.updateLedger(ledger);

        return "redirect:/ledger/detail?id=" + ledger.getId();
    }

    //삭제
    @PostMapping("/ledger/delete")
    public String delete(@RequestParam Long id, Model model){

        int result = ledgerService.deleteLedger(id);

        if (result == 0) {
            return "redirect:/ledger/list?error=delete";
        }

        return "redirect:/ledger/list?success=delete";
    }

    @GetMapping("/ledger/list")
    public String ledgerList(
            @RequestParam(required = false) String month,
            Model model) {

        // 아무것도 선택하지 않았으면 현재 월
        if (month == null || month.isEmpty()) {
            month = YearMonth.now().toString();
        }

        // "2026-08" → YearMonth
        YearMonth yearMonth = YearMonth.parse(month);

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        // 월별 가계부
        List<Ledger> ledgerList =
                ledgerService.ledgerListByDate(startDate, endDate);

        // 카테고리별 지출 합계
        List<Map<String, Object>> categoryList =
                ledgerService.sumByCategory(startDate, endDate);

        model.addAttribute("ledgerList", ledgerList);
        model.addAttribute("categoryList", categoryList);

        // 화면의 month input에 다시 표시
        model.addAttribute("selectedMonth", month);

        // 월별 조회
        model.addAttribute("all", false);

        return "list";
    }

    @GetMapping("/ledger/average")
    public String averageExpense(
            @RequestParam(defaultValue = "1") int months,
            Model model) {

        if (months != 1 && months != 3 && months != 6 && months != 12) {
            months = 1;
        }

        // 전체 월 평균 지출
        double avgMonthlyExpense =
                ledgerService.avgMonthlyExpense(months);

        // 카테고리별 평균 지출
        List<Map<String, Object>> categoryAverageList =
                ledgerService.avgExpenseByCategory(months);

        model.addAttribute("avgMonthlyExpense", avgMonthlyExpense);
        model.addAttribute("categoryAverageList", categoryAverageList);
        model.addAttribute("months", months);

        // 콤마가 들어간 금액
        model.addAttribute(
                "avgMonthlyExpenseFormatted",
                String.format("%,.0f", avgMonthlyExpense)
        );


        return "average";
    }
}
