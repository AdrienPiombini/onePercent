package com.onepercent.goaltracker.domain.dto;

import com.onepercent.goaltracker.domain.entities.TaskStatus;

import java.util.UUID;

public record TaskDto(
        UUID id, String title, String description, TaskStatus status
) {
}
