package com.progweb.aureus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progweb.aureus.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
}
