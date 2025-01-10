package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.TaskDto;
import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.domain.entities.TaskStatus;
import com.onepercent.goaltracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return Task.builder()
                .id(UUID.fromString(taskDto.id()))
                .title(taskDto.title())
                .description(taskDto.description())
                .status(taskDto.status())
                .goalId(UUID.fromString(taskDto.goalId()))
                .build();
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId().toString(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getGoalId().toString());
    }
}
