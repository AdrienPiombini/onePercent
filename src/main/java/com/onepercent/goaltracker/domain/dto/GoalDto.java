package com.onepercent.goaltracker.domain.dto;

import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.UUID;

public record GoalDto(
        String id,
        @NotBlank(message = "title is required")
        String title,
        String description,
        String userId
) {
}
