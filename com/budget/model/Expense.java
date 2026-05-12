package com.budget.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@PrimaryKeyJoinColumn(name = "transaction_id")
public class Expense extends Transaction {

    @Column(nullable = false)
    private boolean exceedsBudget = false;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public Expense() {}

    public Expense(boolean isRecurring, double amount, LocalDate date,
                   String description, Account account, Category category) {
        super(isRecurring, amount, date, description, account, category);
    }

    // -------------------------------------------------------------------------
    // Métodos del dominio
    // -------------------------------------------------------------------------

    public void assignCategory(Category category) {
        this.setCategory(category);
    }

    /**
     * Indica si el gasto supera el límite de presupuesto asignado.
     */
    public boolean checkLimit() {
        return this.exceedsBudget;
    }

    @Override
    public String getType() {
        return "Expense";
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public boolean isExceedsBudget() { return exceedsBudget; }
    public void setExceedsBudget(boolean exceedsBudget) { this.exceedsBudget = exceedsBudget; }
}
