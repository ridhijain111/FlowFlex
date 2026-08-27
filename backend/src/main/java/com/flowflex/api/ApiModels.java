package com.flowflex.api;

import java.time.LocalDate;
import java.util.List;

public final class ApiModels {
    private ApiModels() {}
    public record IncomePoint(LocalDate date, double amount) {}
    public record Recommendation(String status, double savings, double repayment, double available, double essentialReserve, boolean shockMode, String message) {}
    public record Analytics(List<IncomePoint> history, double average, double highest, LocalDate highestDate, double lowest, LocalDate lowestDate, double volatilityScore, String volatilityLevel) {}
    public record SavingsBuffer(double currentSavings, double targetSavings, double dailyEssentialExpenses, double protectedDays, double progressPercent) {}
    public record Loan(double outstanding, double monthlyTarget, double contributed, double progressPercent, List<RepaymentOption> options) {}
    public record RepaymentOption(String status, double amount, String description) {}
    public record ShockStatus(boolean shockMode, double recentAverage, double historicalAverage, String title, String message, List<String> actions) {}
    public record Dashboard(String name, String occupation, double todaysIncome, Recommendation recommendation, double volatilityScore, SavingsBuffer savingsBuffer, Loan loan, ShockStatus shockStatus) {}
    public record IncomeRequest(LocalDate date, double amount) {}
    public record Acceptance(boolean accepted, Recommendation recommendation, String message) {}
}
