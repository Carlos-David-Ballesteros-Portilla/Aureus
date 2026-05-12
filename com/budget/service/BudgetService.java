package com.budget.service;

import com.budget.model.Account;
import com.budget.model.Budget;
import com.budget.model.Category;
import com.budget.repository.BudgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final AccountService   accountService;
    private final CategoryService  categoryService;

    public BudgetService(BudgetRepository budgetRepository,
                         AccountService accountService,
                         CategoryService categoryService) {
        this.budgetRepository = budgetRepository;
        this.accountService   = accountService;
        this.categoryService  = categoryService;
    }

    public List<Budget> findByAccount(Long accountId) {
        return budgetRepository.findByAccountId(accountId);
    }

    public Budget findById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Presupuesto no encontrado con id: " + id));
    }

    public Budget save(Long accountId, Long categoryId, double limitAmount,
                       LocalDate startDate, LocalDate endDate) {
        Account account   = accountService.findById(accountId);
        Category category = categoryService.findById(categoryId);

        Budget budget = new Budget(limitAmount, startDate, endDate, account, category);
        return budgetRepository.save(budget);
    }

    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }
}
