package com.flowflex.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_loans")
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(optional = false) private User user;
    private boolean active;
    private double outstanding;
    private double monthlyTarget;
    private int remainingMonths;
    private double contributed;
    protected Loan() {}
    public Loan(User user, boolean active, double outstanding, double monthlyTarget, int remainingMonths) { this.user = user; this.active = active; this.outstanding = outstanding; this.monthlyTarget = monthlyTarget; this.remainingMonths = remainingMonths; }
    public boolean isActive() { return active; }
    public double getOutstanding() { return outstanding; }
    public double getMonthlyTarget() { return monthlyTarget; }
    public int getRemainingMonths() { return remainingMonths; }
    public double getContributed() { return contributed; }
    public void contribute(double amount) { contributed += amount; outstanding = Math.max(0, outstanding - amount); }
    public void update(boolean active, double outstanding, double monthlyTarget, int remainingMonths) { this.active=active; this.outstanding=outstanding; this.monthlyTarget=monthlyTarget; this.remainingMonths=remainingMonths; }
}
