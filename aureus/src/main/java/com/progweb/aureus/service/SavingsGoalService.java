package com.progweb.aureus.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.progweb.aureus.model.SavingsGoal;
import com.progweb.aureus.model.User;
import com.progweb.aureus.repository.SavingsGoalRepository;

@Service
@Transactional
public class SavingsGoalService {

    private final SavingsGoalRepository savingsGoalRepository;
    private final UserService           userService;

    public SavingsGoalService(SavingsGoalRepository savingsGoalRepository, UserService userService) {
        this.savingsGoalRepository = savingsGoalRepository;
        this.userService           = userService;
    }

    public List<SavingsGoal> findByUser(Long userId) {
        return savingsGoalRepository.findByUserId(userId);
    }

    public SavingsGoal findById(Long id) {
        return savingsGoalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Meta de ahorro no encontrada con id: " + id));
    }

    public SavingsGoal save(Long userId, String goalName, double targetAmount,
                            double currentAmount, LocalDate deadline) {
        User user = userService.findById(userId);
        SavingsGoal goal = new SavingsGoal(goalName, targetAmount, currentAmount, deadline, user);
        return savingsGoalRepository.save(goal);
    }

    public SavingsGoal update(Long id, double currentAmount) {
        SavingsGoal goal = findById(id);
        goal.setCurrentAmount(currentAmount);
        return savingsGoalRepository.save(goal);
    }

    public void delete(Long id) {
        savingsGoalRepository.deleteById(id);
    }
}
