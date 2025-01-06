package com.onepercent.goaltracker.services;

import com.onepercent.goaltracker.domain.entities.Goal;

import java.util.List;
import java.util.UUID;

public interface GoalService {
    List<Goal> getAllGoals();
    Goal getGoal(UUID uuid);
    void createGoal(Goal goal);
    void deleteGoal(UUID uuid);
    void updateGoal(UUID uuid, Goal goal);
}
