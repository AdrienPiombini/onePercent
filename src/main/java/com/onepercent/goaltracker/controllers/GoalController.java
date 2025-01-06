package com.onepercent.goaltracker.controllers;

import com.onepercent.goaltracker.domain.dto.GoalDto;
import com.onepercent.goaltracker.mappers.GoalMapper;
import com.onepercent.goaltracker.services.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "goals")
public class GoalController {

    private final GoalService goalService;

    private final GoalMapper goalMapper;

    public GoalController(GoalService goalService, GoalMapper goalMapper) {
        this.goalService = goalService;
        this.goalMapper = goalMapper;
    }

    @GetMapping
    public ResponseEntity<List<GoalDto>> getAllGoals(){
        var goalList = goalService.getAllGoals();
        var goalListDto = goalList.stream().map(goalMapper::toGoalDto).toList();
        return ResponseEntity.ok(goalListDto);
    }

    @GetMapping(path = "/{goal_id}")
    public ResponseEntity<GoalDto> getGoal(@PathVariable("goal_id")UUID uuid){
        var goal = goalService.getGoal(uuid);
        var goalDto = goalMapper.toGoalDto(goal);
        return ResponseEntity.ok(goalDto);
    }

    @PostMapping
    public ResponseEntity<String> createGoal(@RequestBody GoalDto goalDto){
        goalService.createGoal(goalMapper.fromGoalDto(goalDto));
        return ResponseEntity.status(201).build();
    }

    @PostMapping(path = "goal_id")
    public ResponseEntity<String> updateGoal(@PathVariable("goal_id")UUID uuid, @RequestBody GoalDto goalDto){
        var goal = goalMapper.fromGoalDto(goalDto);
        goalService.updateGoal(uuid, goal);
        return ResponseEntity.status(202).build();
    }

    @DeleteMapping("/{goal_id}")
    public ResponseEntity<String> deleteGoal(@PathVariable("goal_id") UUID uuid){
        goalService.deleteGoal(uuid);
        return ResponseEntity.noContent().build();
    }
}
