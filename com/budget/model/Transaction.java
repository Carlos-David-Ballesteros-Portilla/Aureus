package com.budget.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isRecurring;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public Transaction() {}

    public Transaction(boolean isRecurring, double amount, LocalDate date,
                       String description, Account account, Category category) {
        this.isRecurring = isRecurring;
        this.amount      = amount;
        this.date        = date;
        this.description = description;
        this.account     = account;
        this.category    = category;
    }

    // -------------------------------------------------------------------------
    // Método del dominio
    // -------------------------------------------------------------------------

    /**
     * Devuelve el tipo de transacción como cadena.
     * Las subclases deben sobreescribir este método.
     */
    public abstract String getType();

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public Long getId() { return id; }

    public boolean isRecurring() { return isRecurring; }
    public void setRecurring(boolean recurring) { isRecurring = recurring; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
