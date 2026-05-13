package com.progweb.aureus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progweb.aureus.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByAccountId(Long accountId);
}
