package com.progweb.aureus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progweb.aureus.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByAccountId(Long accountId);
    List<Expense> findByAccountIdAndCategoryId(Long accountId, Long categoryId);
}
