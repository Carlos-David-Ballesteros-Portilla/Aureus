package com.progweb.aureus.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.progweb.aureus.model.Account;
import com.progweb.aureus.model.Category;
import com.progweb.aureus.model.Expense;
import com.progweb.aureus.model.Income;
import com.progweb.aureus.model.Transaction;
import com.progweb.aureus.repository.ExpenseRepository;
import com.progweb.aureus.repository.IncomeRepository;
import com.progweb.aureus.repository.TransactionRepository;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ExpenseRepository     expenseRepository;
    private final IncomeRepository      incomeRepository;
    private final AccountService        accountService;
    private final CategoryService       categoryService;

    public TransactionService(TransactionRepository transactionRepository,
                              ExpenseRepository expenseRepository,
                              IncomeRepository incomeRepository,
                              AccountService accountService,
                              CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.expenseRepository     = expenseRepository;
        this.incomeRepository      = incomeRepository;
        this.accountService        = accountService;
        this.categoryService       = categoryService;
    }

    public List<Transaction> findByAccount(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transacción no encontrada con id: " + id));
    }

    /**
     * Registra un nuevo gasto en una cuenta.
     */
    public Expense saveExpense(Long accountId, Long categoryId, double amount,
                               LocalDate date, String description, boolean isRecurring) {
        Account account   = accountService.findById(accountId);
        Category category = categoryService.findById(categoryId);

        Expense expense = new Expense(isRecurring, amount, date, description, account, category);
        return expenseRepository.save(expense);
    }

    /**
     * Registra un nuevo ingreso en una cuenta.
     */
    public Income saveIncome(Long accountId, Long categoryId, double amount,
                             LocalDate date, String description, String source, boolean isRecurring) {
        Account account   = accountService.findById(accountId);
        Category category = categoryService.findById(categoryId);

        Income income = new Income(source, isRecurring, amount, date, description, account, category);
        return incomeRepository.save(income);
    }

    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
}
