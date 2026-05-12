package com.budget.repository;

import com.budget.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByAccountId(Long accountId);
    List<Expense> findByAccountIdAndCategoryId(Long accountId, Long categoryId);
}
