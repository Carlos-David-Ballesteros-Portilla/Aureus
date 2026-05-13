package com.progweb.aureus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progweb.aureus.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByAccountId(Long accountId);
    List<Budget> findByAccountIdAndCategoryId(Long accountId, Long categoryId);
}
