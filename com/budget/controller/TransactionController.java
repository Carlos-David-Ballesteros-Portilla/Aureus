package com.budget.controller;

import com.budget.service.AccountService;
import com.budget.service.CategoryService;
import com.budget.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final AccountService     accountService;
    private final CategoryService    categoryService;

    public TransactionController(TransactionService transactionService,
                                 AccountService accountService,
                                 CategoryService categoryService) {
        this.transactionService = transactionService;
        this.accountService     = accountService;
        this.categoryService    = categoryService;
    }

    @GetMapping("/account/{accountId}")
    public String listByAccount(@PathVariable Long accountId, Model model) {
        model.addAttribute("transactions", transactionService.findByAccount(accountId));
        model.addAttribute("account", accountService.findById(accountId));
        return "transactions/list";
    }

    @GetMapping("/new/expense")
    public String expenseForm(@RequestParam Long accountId, Model model) {
        model.addAttribute("accountId", accountId);
        model.addAttribute("categories", categoryService.findAll());
        return "transactions/expense-form";
    }

    @PostMapping("/expense")
    public String createExpense(@RequestParam Long accountId,
                                @RequestParam Long categoryId,
                                @RequestParam double amount,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                @RequestParam String description,
                                @RequestParam(defaultValue = "false") boolean isRecurring) {
        transactionService.saveExpense(accountId, categoryId, amount, date, description, isRecurring);
        return "redirect:/transactions/account/" + accountId;
    }

    @GetMapping("/new/income")
    public String incomeForm(@RequestParam Long accountId, Model model) {
        model.addAttribute("accountId", accountId);
        model.addAttribute("categories", categoryService.findAll());
        return "transactions/income-form";
    }

    @PostMapping("/income")
    public String createIncome(@RequestParam Long accountId,
                               @RequestParam Long categoryId,
                               @RequestParam double amount,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               @RequestParam String description,
                               @RequestParam String source,
                               @RequestParam(defaultValue = "false") boolean isRecurring) {
        transactionService.saveIncome(accountId, categoryId, amount, date, description, source, isRecurring);
        return "redirect:/transactions/account/" + accountId;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @RequestParam Long accountId) {
        transactionService.delete(id);
        return "redirect:/transactions/account/" + accountId;
    }
}
