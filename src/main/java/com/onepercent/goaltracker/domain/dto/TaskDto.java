package com.onepercent.goaltracker.domain.dto;

import com.onepercent.goaltracker.domain.entities.TaskStatus;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record TaskDto(
        String id,
        @NotBlank(message = "title is required")
        String title,
        String description,
        TaskStatus status,
        String goalId
) {
}
