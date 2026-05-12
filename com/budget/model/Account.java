package com.budget.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Budget> budgets = new ArrayList<>();

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public Account() {}

    public Account(String name, User user) {
        this.name = name;
        this.user = user;
    }

    // -------------------------------------------------------------------------
    // Métodos del dominio
    // -------------------------------------------------------------------------

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Filtra transacciones por mes y año.
     * @param month 1-12
     */
    public List<Transaction> getTransactionsByMonth(int month, int year) {
        return transactions.stream()
                .filter(t -> {
                    java.time.LocalDate d = t.getDate();
                    return d.getMonthValue() == month && d.getYear() == year;
                })
                .toList();
    }

    /**
     * Suma los gastos de una categoría específica.
     */
    public double getExpensesByCategory(Category category) {
        return transactions.stream()
                .filter(t -> t instanceof Expense
                        && t.getCategory() != null
                        && t.getCategory().getId().equals(category.getId()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    /**
     * Suma los gastos de un mes y año específicos.
     * @param month 1-12
     */
    public double getMonthlyExpenses(int month, int year) {
        return getTransactionsByMonth(month, year).stream()
                .filter(t -> t instanceof Expense)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    /**
     * Saldo neto: ingresos - gastos.
     */
    public double getBalance() {
        return transactions.stream().mapToDouble(t -> {
            if (t instanceof Income)  return  t.getAmount();
            if (t instanceof Expense) return -t.getAmount();
            return 0;
        }).sum();
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }

    public List<Budget> getBudgets() { return budgets; }
    public void setBudgets(List<Budget> budgets) { this.budgets = budgets; }
}
