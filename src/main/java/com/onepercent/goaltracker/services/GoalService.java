package com.onepercent.goaltracker.services;

import com.onepercent.goaltracker.Utils.ServiceResult;
import com.onepercent.goaltracker.domain.entities.Goal;

import java.util.List;
import java.util.UUID;

public interface GoalService {
    ServiceResult<List<Goal>> getAllGoals();
    ServiceResult<Goal> getGoal(UUID uuid);
    ServiceResult<?> createGoal(Goal goal);
    ServiceResult<?> deleteGoal(UUID uuid);
    ServiceResult<?> updateGoal(UUID uuid, Goal goal);
}
