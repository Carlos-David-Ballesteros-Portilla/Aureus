package com.budget.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "savings_goals")
public class SavingsGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String goalName;

    @Column(nullable = false)
    private double targetAmount;

    @Column(nullable = false)
    private double currentAmount;

    @Column(nullable = false)
    private LocalDate deadline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // -------------------------------------------------------------------------
    // Constructores
    // -------------------------------------------------------------------------

    public SavingsGoal() {}

    public SavingsGoal(String goalName, double targetAmount, double currentAmount,
                       LocalDate deadline, User user) {
        this.goalName      = goalName;
        this.targetAmount  = targetAmount;
        this.currentAmount = currentAmount;
        this.deadline      = deadline;
        this.user          = user;
    }

    // -------------------------------------------------------------------------
    // Métodos del dominio
    // -------------------------------------------------------------------------

    /**
     * Calcula el porcentaje de progreso hacia la meta de ahorro.
     * @return valor entre 0.0 y 100.0
     */
    public double calculateProgress() {
        if (targetAmount <= 0) return 0;
        return Math.min((currentAmount / targetAmount) * 100, 100);
    }

    /**
     * Indica si la meta de ahorro se ha cumplido.
     */
    public boolean checkCompletion() {
        return currentAmount >= targetAmount;
    }

    // -------------------------------------------------------------------------
    // Getters y Setters
    // -------------------------------------------------------------------------

    public Long getId() { return id; }

    public String getGoalName() { return goalName; }
    public void setGoalName(String goalName) { this.goalName = goalName; }

    public double getTargetAmount() { return targetAmount; }
    public void setTargetAmount(double targetAmount) { this.targetAmount = targetAmount; }

    public double getCurrentAmount() { return currentAmount; }
    public void setCurrentAmount(double currentAmount) { this.currentAmount = currentAmount; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
