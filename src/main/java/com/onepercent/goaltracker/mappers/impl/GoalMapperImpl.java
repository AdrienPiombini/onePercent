package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.GoalDto;
import com.onepercent.goaltracker.domain.dto.TaskDto;
import com.onepercent.goaltracker.domain.entities.Goal;
import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.mappers.GoalMapper;
import com.onepercent.goaltracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoalMapperImpl implements GoalMapper {

    private final TaskMapper taskMapper;
    public GoalMapperImpl(final TaskMapperImpl taskMapper){
        this.taskMapper = taskMapper;
    }

    public Goal fromGoalDto(GoalDto goalDto) {
        List<Task> taskList = goalDto.taskDtoList().stream().map(taskMapper::fromDto).toList();
        return Goal.builder()
                .id(goalDto.id())
                .title(goalDto.title())
                .description(goalDto.description())
                .tasks(taskList)
                .build();
    }

    public GoalDto toGoalDto(Goal goal) {
        List<TaskDto> taskDtoList = goal.getTasks().stream().map(taskMapper::toDto).toList();
        return new GoalDto(goal.getId(), goal.getTitle(), goal.getDescription(), taskDtoList);
    }
}
