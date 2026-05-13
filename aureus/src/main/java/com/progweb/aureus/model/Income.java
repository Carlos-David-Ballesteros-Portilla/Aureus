package com.progweb.aureus.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
@PrimaryKeyJoinColumn(name = "transaction_id")
public class Income extends Transaction {

    @Column(nullable = false)
    private String source;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public Income() {}

    public Income(String source, boolean isRecurring, double amount, LocalDate date,
                  String description, Account account, Category category) {
        super(isRecurring, amount, date, description, account, category);
        this.source = source;
    }

    // -------------------------------------------------------------------------
    // Métodos del dominio
    // -------------------------------------------------------------------------

    public void assignCategory(Category category) {
        this.setCategory(category);
    }

    @Override
    public String getType() {
        return "Income";
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
