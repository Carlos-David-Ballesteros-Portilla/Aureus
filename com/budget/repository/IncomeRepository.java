package com.budget.repository;

import com.budget.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByAccountId(Long accountId);
}
