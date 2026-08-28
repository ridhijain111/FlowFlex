package com.flowflex.finance;

import com.flowflex.api.ApiModels.*;
import com.flowflex.income.IncomeRecord;
import com.flowflex.income.IncomeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinanceService {
    private final IncomeRepository repository;
    private final FinancialRecommendationService recommendationService;
    private final IncomeShockService shockService;
    private static final double SAVINGS = 4200;
    private static final double TARGET_SAVINGS = 8400;
    private static final double ESSENTIALS = 600;
    private static final double LOAN = 38000;
    private static final double MONTHLY_TARGET = 6000;
    private static final double CONTRIBUTED = 12800;

    public FinanceService(IncomeRepository repository, FinancialRecommendationService recommendationService, IncomeShockService shockService) {
        this.repository = repository; this.recommendationService = recommendationService; this.shockService = shockService;
    }
    public List<IncomeRecord> records() { return repository.findAllByOrderByIncomeDateAsc(); }
    public IncomeRecord save(IncomeRecord record) { return repository.save(record); }
    public Analytics analytics() {
        List<IncomeRecord> allRecords = records();
        List<IncomeRecord> records = allRecords.subList(Math.max(0, allRecords.size() - 30), allRecords.size());
        List<IncomePoint> points = records.stream().map(r -> new IncomePoint(r.getIncomeDate(), r.getAmount())).toList();
        double average = average(records.stream().map(IncomeRecord::getAmount).toList());
        double deviation = Math.sqrt(records.stream().mapToDouble(r -> Math.pow(r.getAmount() - average, 2)).average().orElse(0));
        double cv = average == 0 ? 0 : deviation / average;
        IncomeRecord highest = records.stream().max((a,b) -> Double.compare(a.getAmount(), b.getAmount())).orElse(null);
        IncomeRecord lowest = records.stream().min((a,b) -> Double.compare(a.getAmount(), b.getAmount())).orElse(null);
        return new Analytics(points, round(average), highest == null ? 0 : highest.getAmount(), highest == null ? null : highest.getIncomeDate(), lowest == null ? 0 : lowest.getAmount(), lowest == null ? null : lowest.getIncomeDate(), round(cv), cv < .35 ? "LOW" : cv < .7 ? "MODERATE" : "HIGH");
    }
    public ShockStatus shock() {
        List<IncomeRecord> allRecords = records();
        List<IncomeRecord> lastThirty = allRecords.subList(Math.max(0, allRecords.size() - 30), allRecords.size());
        List<IncomeRecord> lastSeven = lastThirty.subList(Math.max(0, lastThirty.size() - 7), lastThirty.size());
        return shockService.assess(amounts(lastSeven), amounts(lastThirty));
    }
    public Recommendation recommendation() {
        Analytics a = analytics(); List<IncomeRecord> all = records(); double today = all.isEmpty() ? 0 : all.get(all.size() - 1).getAmount();
        return recommendationService.recommend(today, amounts(all), a.average(), a.volatilityScore(), SAVINGS, TARGET_SAVINGS, ESSENTIALS, LOAN, MONTHLY_TARGET, shock().shockMode());
    }
    public SavingsBuffer savings() { return new SavingsBuffer(SAVINGS, TARGET_SAVINGS, ESSENTIALS, round(SAVINGS / ESSENTIALS), round(Math.min(100, SAVINGS / TARGET_SAVINGS * 100))); }
    public Loan loan() { double progress = CONTRIBUTED / (CONTRIBUTED + LOAN) * 100; return new Loan(LOAN, MONTHLY_TARGET, CONTRIBUTED, round(progress), List.of(new RepaymentOption("HIGH", 400, "Use stronger days to reduce the balance faster"), new RepaymentOption("NORMAL", 150, "Make a steady contribution without squeezing essentials"), new RepaymentOption("LOW", 0, "Keep cash available for essential expenses"))); }
    public Dashboard dashboard() {
        Analytics a = analytics();
        List<IncomeRecord> all = records();
        double today = all.isEmpty() ? 0 : all.get(all.size() - 1).getAmount();
        return new Dashboard("Ravi", "Delivery worker", today, recommendation(), a.volatilityScore(), savings(), loan(), shock());
    }
    private List<Double> amounts(List<IncomeRecord> values) { return values.stream().map(IncomeRecord::getAmount).toList(); }
    private double average(List<Double> values) { return values.stream().mapToDouble(Double::doubleValue).average().orElse(0); }
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }
}
