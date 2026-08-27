package com.flowflex.api;

import com.flowflex.api.ApiModels.*;
import com.flowflex.finance.FinanceService;
import com.flowflex.income.IncomeRecord;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlowFlexController {
    private final FinanceService finance;
    public FlowFlexController(FinanceService finance) { this.finance = finance; }
    @GetMapping("/dashboard") public Dashboard dashboard() { return finance.dashboard(); }
    @GetMapping("/income/history") public List<IncomePoint> history() { return finance.analytics().history(); }
    @PostMapping("/income") public IncomePoint addIncome(@RequestBody IncomeRequest request) { if (request.amount() < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Income cannot be negative"); IncomeRecord saved = finance.save(new IncomeRecord(request.date() == null ? LocalDate.now() : request.date(), request.amount())); return new IncomePoint(saved.getIncomeDate(), saved.getAmount()); }
    @GetMapping("/analytics") public Analytics analytics() { return finance.analytics(); }
    @GetMapping("/recommendation") public Recommendation recommendation() { return finance.recommendation(); }
    @PostMapping("/recommendation/accept") public Acceptance accept() { Recommendation recommendation = finance.recommendation(); return new Acceptance(true, recommendation, "Recommendation accepted for today's plan."); }
    @GetMapping("/savings-buffer") public SavingsBuffer savings() { return finance.savings(); }
    @GetMapping("/loan") public Loan loan() { return finance.loan(); }
    @GetMapping("/shock-status") public ShockStatus shock() { return finance.shock(); }
}
