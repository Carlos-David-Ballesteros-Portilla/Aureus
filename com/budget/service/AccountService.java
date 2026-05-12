package com.budget.service;

import com.budget.model.Account;
import com.budget.model.User;
import com.budget.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    public AccountService(AccountRepository accountRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService       = userService;
    }

    public List<Account> findByUser(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con id: " + id));
    }

    public Account save(String name, Long userId) {
        User user = userService.findById(userId);
        Account account = new Account(name, user);
        return accountRepository.save(account);
    }

    public Account update(Long id, String name) {
        Account existing = findById(id);
        existing.setName(name);
        return accountRepository.save(existing);
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
