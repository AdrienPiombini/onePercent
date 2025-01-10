package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.TaskDto;
import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.domain.entities.TaskStatus;
import com.onepercent.goaltracker.mappers.TaskMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TaskMapperImplTest {

    private final TaskMapper taskMapper = new TaskMapperImpl();

    @Test
    void fromDto() {
        var taskDto = new TaskDto(UUID.randomUUID().toString(), "Do push up", "4 set of 10", TaskStatus.TODO, UUID.randomUUID().toString());

        var result = taskMapper.fromDto(taskDto);

        assertThat(result.getId().toString()).isEqualTo(taskDto.id());
        assertThat(result.getTitle()).isEqualTo(taskDto.title());
        assertThat(result.getDescription()).isEqualTo(taskDto.description());
        assertThat(result.getStatus()).isEqualTo(taskDto.status());
        assertThat(result.getGoalId().toString()).isEqualTo(taskDto.goalId());
    }

    @Test
    void toDto() {
        var task = Task.builder()
                .id(UUID.randomUUID())
                .title("Do Push up")
                .description("10 set of 5")
                .status(TaskStatus.DONE)
                .goalId(UUID.randomUUID())
                .build();

        var result = taskMapper.toDto(task);

        assertThat(result.id()).isEqualTo(task.getId().toString());
        assertThat(result.title()).isEqualTo(task.getTitle());
        assertThat(result.description()).isEqualTo(task.getDescription());
        assertThat(result.status()).isEqualTo(task.getStatus());
        assertThat(result.goalId()).isEqualTo(task.getGoalId().toString());
    }
}