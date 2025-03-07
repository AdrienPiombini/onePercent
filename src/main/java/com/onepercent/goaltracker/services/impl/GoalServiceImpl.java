package com.onepercent.goaltracker.services.impl;

import com.onepercent.goaltracker.utils.ServiceResult;
import com.onepercent.goaltracker.domain.entities.Goal;
import com.onepercent.goaltracker.repositories.GoalRepository;
import com.onepercent.goaltracker.repositories.UserRepository;
import com.onepercent.goaltracker.services.GoalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class GoalServiceImpl implements GoalService {
    private static final Logger log = LoggerFactory.getLogger(GoalServiceImpl.class);
    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public GoalServiceImpl(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ServiceResult<List<Goal>> getAllGoals() {
        var result = goalRepository.findAll();

        return ServiceResult.ok(result);
    }

    @Override
    public ServiceResult<Goal> getGoal(UUID uuid) {
        var result = goalRepository.findById(uuid);
        return result.map(ServiceResult::ok)
                .orElseGet(() -> ServiceResult.error("this id does not exist"));
    }

    @Override
    public ServiceResult<?> createGoal(Goal goal) {
        var canCreate = canCreate(goal);
        if(! canCreate){
            return ServiceResult.error("Goal should have valid user, and non-existent id");
        }

        goal.setCreated(LocalDateTime.now());
        goal.setUpdated(LocalDateTime.now());

        log.info("Creation of goals title {} for this user {}", goal.getTitle(), goal.getUserId());
        goalRepository.save(goal);

        return ServiceResult.ok(null);
    }

    @Override
    public ServiceResult<?> deleteGoal(UUID uuid) {
        log.info("Delete goals id {}", uuid);
        goalRepository.deleteById(uuid);

        return ServiceResult.ok(null);
    }

    @Override
    public ServiceResult<?> updateGoal(UUID uuid, Goal goal) {
        var canUpdate = canUpdate(uuid);

        if(! canUpdate){
            return ServiceResult.error(String.format("UUID : %s is not valid", uuid));
        }
        log.info("Update of goals title {} for this user {}", goal.getTitle(), goal.getUserId());
        goalRepository.save(goal);
        return ServiceResult.ok(null);
    }

    private boolean canUpdate(UUID uuid){
        return goalRepository.existsById(uuid);
    }

    private boolean canCreate(Goal goal){
        if(goal.getId() != null) return false;
        return userRepository.existsById(goal.getUserId());
    }

}
