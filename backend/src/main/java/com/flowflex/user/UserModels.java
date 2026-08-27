package com.flowflex.user;

import com.flowflex.api.ApiModels.*;
import java.time.LocalDate;
import java.util.List;

public final class UserModels {
    private UserModels() {}
    public record CreateUser(String name, String occupation, String incomeType) {}
    public record ProfileRequest(String earningFrequency, Double dailyEssentialExpenses, Double monthlyEssentialExpenses, double currentSavings, int protectionDays) {}
    public record LoanRequest(boolean hasLoan, double outstanding, double monthlyTarget, int remainingMonths) {}
    public record IncomeRequest(LocalDate date, double amount) {}
    public record ProfileResponse(String earningFrequency, double dailyEssentialExpenses, double currentSavings, int protectionDays, double targetSavings) {}
    public record UserResponse(Long id, String name, String occupation, String incomeType, ProfileResponse profile, com.flowflex.api.ApiModels.Loan loan) {}
    public record PersonalizedSummary(UserResponse user, List<IncomePoint> history, Analytics analytics, Recommendation recommendation, SavingsBuffer savingsBuffer, com.flowflex.api.ApiModels.Loan loan, ShockStatus shockStatus, boolean acceptedToday) {}
    public record CustomizeRequest(double savings, double repayment) {}
}
