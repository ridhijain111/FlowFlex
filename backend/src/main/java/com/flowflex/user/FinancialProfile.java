package com.flowflex.user;

import jakarta.persistence.*;

@Entity
@Table(name = "financial_profiles")
public class FinancialProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(optional = false) private User user;
    private String earningFrequency;
    private double dailyEssentialExpenses;
    private double currentSavings;
    private int protectionDays;
    protected FinancialProfile() {}
    public FinancialProfile(User user, String earningFrequency, double dailyEssentialExpenses, double currentSavings, int protectionDays) { this.user = user; this.earningFrequency = earningFrequency; this.dailyEssentialExpenses = dailyEssentialExpenses; this.currentSavings = currentSavings; this.protectionDays = protectionDays; }
    public double getDailyEssentialExpenses() { return dailyEssentialExpenses; }
    public double getCurrentSavings() { return currentSavings; }
    public int getProtectionDays() { return protectionDays; }
    public String getEarningFrequency() { return earningFrequency; }
    public void update(String earningFrequency, double dailyEssentialExpenses, double currentSavings, int protectionDays) { this.earningFrequency = earningFrequency; this.dailyEssentialExpenses = dailyEssentialExpenses; this.currentSavings = currentSavings; this.protectionDays = protectionDays; }
}
