package com.flowflex.user;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contribution_transactions", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "contribution_date"}))
public class ContributionTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) private User user;
    @Column(name = "contribution_date") private LocalDate contributionDate;
    private double savings;
    private double repayment;
    protected ContributionTransaction() {}
    public ContributionTransaction(User user, LocalDate date, double savings, double repayment) { this.user=user; this.contributionDate=date; this.savings=savings; this.repayment=repayment; }
}
