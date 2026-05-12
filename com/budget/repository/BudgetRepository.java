package com.budget.repository;

import com.budget.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByAccountId(Long accountId);
    List<Budget> findByAccountIdAndCategoryId(Long accountId, Long categoryId);
}
