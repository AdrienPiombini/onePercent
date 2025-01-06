package com.onepercent.goaltracker.domain.dto;

import java.util.List;
import java.util.UUID;

public record GoalDto(
        String id, String title, String description, List<TaskDto> taskDtoList
) {
}
