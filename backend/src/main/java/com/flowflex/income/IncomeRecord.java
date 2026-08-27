package com.flowflex.income;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "income_records")
public class IncomeRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private LocalDate incomeDate;
    @Column(nullable = false) private double amount;

    protected IncomeRecord() {}
    public IncomeRecord(LocalDate incomeDate, double amount) { this.incomeDate = incomeDate; this.amount = amount; }
    public Long getId() { return id; }
    public LocalDate getIncomeDate() { return incomeDate; }
    public double getAmount() { return amount; }
}
