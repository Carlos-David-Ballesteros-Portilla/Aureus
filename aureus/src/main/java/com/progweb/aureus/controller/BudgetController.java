package com.progweb.aureus.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.progweb.aureus.service.AccountService;
import com.progweb.aureus.service.BudgetService;
import com.progweb.aureus.service.CategoryService;

@Controller
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService   budgetService;
    private final AccountService  accountService;
    private final CategoryService categoryService;

    public BudgetController(BudgetService budgetService,
                            AccountService accountService,
                            CategoryService categoryService) {
        this.budgetService   = budgetService;
        this.accountService  = accountService;
        this.categoryService = categoryService;
    }

    @GetMapping("/account/{accountId}")
    public String listByAccount(@PathVariable Long accountId, Model model) {
        model.addAttribute("budgets", budgetService.findByAccount(accountId));
        model.addAttribute("account", accountService.findById(accountId));
        return "budgets/list";
    }

    @GetMapping("/new")
    public String createForm(@RequestParam Long accountId, Model model) {
        model.addAttribute("accountId", accountId);
        model.addAttribute("categories", categoryService.findAll());
        return "budgets/form";
    }

    @PostMapping
    public String create(@RequestParam Long accountId,
                         @RequestParam Long categoryId,
                         @RequestParam double limitAmount,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        budgetService.save(accountId, categoryId, limitAmount, startDate, endDate);
        return "redirect:/budgets/account/" + accountId;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @RequestParam Long accountId) {
        budgetService.delete(id);
        return "redirect:/budgets/account/" + accountId;
    }
}
