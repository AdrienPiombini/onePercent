package com.onepercent.goaltracker.domain.dto;

import com.onepercent.goaltracker.domain.entities.Goal;

import java.util.List;

public record UserDto(String uuid, String username, List<GoalDto> goalList){

}