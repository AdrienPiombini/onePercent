package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.TaskDto;
import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.domain.entities.TaskStatus;
import com.onepercent.goaltracker.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.id())
                .description(taskDto.description())
                .status(taskDto.status())
                .build();
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatus());
    }
}
