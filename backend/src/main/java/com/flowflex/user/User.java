package com.flowflex.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    @Column(nullable = false) private String occupation;
    @Column(nullable = false) private String incomeType;
    protected User() {}
    public User(String name, String occupation, String incomeType) { this.name = name; this.occupation = occupation; this.incomeType = incomeType; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getOccupation() { return occupation; }
    public String getIncomeType() { return incomeType; }
    public void update(String name, String occupation, String incomeType) { this.name = name; this.occupation = occupation; this.incomeType = incomeType; }
}
