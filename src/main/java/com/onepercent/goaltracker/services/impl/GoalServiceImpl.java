package com.onepercent.goaltracker.services.impl;

import com.onepercent.goaltracker.domain.entities.Goal;
import com.onepercent.goaltracker.repositories.GoalRepository;
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

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }

    @Override
    public Goal getGoal(UUID uuid) {
        var result = goalRepository.findById(uuid);
        if(result.isEmpty()) throw new NullPointerException("this id does not exist");
        return result.get();
    }

    @Override
    public void createGoal(Goal goal) {
        var result = goalRepository.existsById(goal.getId());
        if(result) throw new RuntimeException("Goal already exist");

        goal.setCreated(LocalDateTime.now());
        goal.setUpdated(LocalDateTime.now());

        log.info("Creation of goals title {} for this user {}", goal.getTitle(), goal.getUser().getId());
        goalRepository.save(goal);
    }

    @Override
    public void deleteGoal(UUID uuid) {
        log.info("Delete goals id {}", uuid);
        goalRepository.deleteById(uuid);
    }

    @Override
    public void updateGoal(Goal goal) {
        var result = goalRepository.existsById(goal.getId());
        if(! result){
            throw new NullPointerException(String.format("Goal with id %s does not exist", goal.getId()));
        }
        log.info("Update of goals title {} for this user {}", goal.getTitle(), goal.getUser().getId());
        goalRepository.save(goal);
    }
}
