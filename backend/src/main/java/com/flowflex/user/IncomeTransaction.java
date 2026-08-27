package com.flowflex.user;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "income_transactions")
public class IncomeTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) private User user;
    @Column(nullable = false) private LocalDate transactionDate;
    @Column(nullable = false) private double amount;
    protected IncomeTransaction() {}
    public IncomeTransaction(User user, LocalDate transactionDate, double amount) { this.user=user; this.transactionDate=transactionDate; this.amount=amount; }
    public Long getId() { return id; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public double getAmount() { return amount; }
    public void update(LocalDate date, double amount) { this.transactionDate=date; this.amount=amount; }
}
