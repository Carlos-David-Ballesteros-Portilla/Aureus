package com.progweb.aureus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.progweb.aureus.service.AccountService;
import com.progweb.aureus.service.UserService;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final UserService    userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService    = userService;
    }

    @GetMapping("/user/{userId}")
    public String listByUser(@PathVariable Long userId, Model model) {
        model.addAttribute("accounts", accountService.findByUser(userId));
        model.addAttribute("user", userService.findById(userId));
        return "accounts/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.findById(id));
        return "accounts/detail";
    }

    @GetMapping("/new")
    public String createForm(@RequestParam Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "accounts/form";
    }

    @PostMapping
    public String create(@RequestParam String name, @RequestParam Long userId) {
        accountService.save(name, userId);
        return "redirect:/accounts/user/" + userId;
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.findById(id));
        return "accounts/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @RequestParam String name) {
        accountService.update(id, name);
        return "redirect:/accounts/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @RequestParam Long userId) {
        accountService.delete(id);
        return "redirect:/accounts/user/" + userId;
    }
}
