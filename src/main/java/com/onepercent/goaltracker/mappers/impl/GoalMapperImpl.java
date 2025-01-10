package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.GoalDto;
import com.onepercent.goaltracker.domain.dto.TaskDto;
import com.onepercent.goaltracker.domain.entities.Goal;
import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.mappers.GoalMapper;
import com.onepercent.goaltracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GoalMapperImpl implements GoalMapper {

    public Goal fromGoalDto(GoalDto goalDto) {
        UUID uuid = null;
        if(goalDto.id() != null) {
            uuid = UUID.fromString(goalDto.id());
        }
        return Goal.builder()
                .id(uuid)
                .title(goalDto.title())
                .description(goalDto.description())
                .userId(UUID.fromString(goalDto.userId()))
                .build();
    }

    public GoalDto toGoalDto(Goal goal) {
        return new GoalDto(goal.getId().toString(), goal.getTitle(), goal.getDescription(), goal.getUserId().toString());
    }
}
