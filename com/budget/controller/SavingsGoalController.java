package com.budget.controller;

import com.budget.service.SavingsGoalService;
import com.budget.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/savings-goals")
public class SavingsGoalController {

    private final SavingsGoalService savingsGoalService;
    private final UserService        userService;

    public SavingsGoalController(SavingsGoalService savingsGoalService, UserService userService) {
        this.savingsGoalService = savingsGoalService;
        this.userService        = userService;
    }

    @GetMapping("/user/{userId}")
    public String listByUser(@PathVariable Long userId, Model model) {
        model.addAttribute("goals", savingsGoalService.findByUser(userId));
        model.addAttribute("user", userService.findById(userId));
        return "savings-goals/list";
    }

    @GetMapping("/new")
    public String createForm(@RequestParam Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "savings-goals/form";
    }

    @PostMapping
    public String create(@RequestParam Long userId,
                         @RequestParam String goalName,
                         @RequestParam double targetAmount,
                         @RequestParam(defaultValue = "0") double currentAmount,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline) {
        savingsGoalService.save(userId, goalName, targetAmount, currentAmount, deadline);
        return "redirect:/savings-goals/user/" + userId;
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("goal", savingsGoalService.findById(id));
        return "savings-goals/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam double currentAmount,
                         @RequestParam Long userId) {
        savingsGoalService.update(id, currentAmount);
        return "redirect:/savings-goals/user/" + userId;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @RequestParam Long userId) {
        savingsGoalService.delete(id);
        return "redirect:/savings-goals/user/" + userId;
    }
}
