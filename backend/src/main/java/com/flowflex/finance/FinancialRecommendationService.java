package com.flowflex.finance;

import com.flowflex.api.ApiModels.Recommendation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialRecommendationService {
    public Recommendation recommend(double todaysIncome, List<Double> recentHistory, double averageIncome,
                                    double volatility, double currentSavings, double targetSavings,
                                    double dailyEssentialExpenses, double outstandingLoan, double monthlyTarget,
                                    boolean shockMode) {
        String status = todaysIncome < averageIncome * 0.6 ? "LOW" : todaysIncome > averageIncome * 1.3 ? "HIGH" : "NORMAL";
        double savingsRate = status.equals("HIGH") ? 0.15 : status.equals("NORMAL") ? 0.08 : 0.02;
        double repaymentRate = status.equals("HIGH") ? 0.10 : status.equals("NORMAL") ? 0.07 : 0.0;
        if (shockMode) {
            savingsRate = Math.min(savingsRate, 0.02);
            repaymentRate = 0.0;
        }
        if (currentSavings < targetSavings && status.equals("HIGH")) savingsRate = 0.18;
        double essentialReserve = Math.min(todaysIncome, dailyEssentialExpenses);
        double distributable = Math.max(0, todaysIncome - essentialReserve);
        double savings = Math.min(distributable * savingsRate, Math.max(0, todaysIncome - essentialReserve));
        double repayment = Math.min(distributable - savings, Math.min(distributable * repaymentRate, Math.min(outstandingLoan, monthlyTarget)));
        double available = Math.max(0, todaysIncome - savings - repayment);
        String message = shockMode ? "Keep cash available for essentials while earnings recover." :
                status.equals("HIGH") ? "A strong day is helping you build breathing room." :
                status.equals("LOW") ? "Essentials come first today. It is okay to contribute less." :
                "A balanced split keeps today's money flexible.";
        return new Recommendation(status, round(savings), round(repayment), round(available), round(essentialReserve), shockMode, message);
    }
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }
}
