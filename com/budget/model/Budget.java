package com.budget.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double limitAmount;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public Budget() {}

    public Budget(double limitAmount, LocalDate startDate, LocalDate endDate,
                  Account account, Category category) {
        this.limitAmount = limitAmount;
        this.startDate   = startDate;
        this.endDate     = endDate;
        this.account     = account;
        this.category    = category;
    }

    // -------------------------------------------------------------------------
    // Métodos del dominio
    // -------------------------------------------------------------------------

    /**
     * Verifica si los gastos acumulados en la categoría superan el límite.
     */
    public boolean checkAlert() {
        double spent = account.getExpensesByCategory(category);
        return spent >= limitAmount;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public Long getId() { return id; }

    public double getLimitAmount() { return limitAmount; }
    public void setLimitAmount(double limitAmount) { this.limitAmount = limitAmount; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
