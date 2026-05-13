package com.progweb.aureus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progweb.aureus.model.SavingsGoal;

public interface SavingsGoalRepository extends JpaRepository<SavingsGoal, Long> {
    List<SavingsGoal> findByUserId(Long userId);
}
