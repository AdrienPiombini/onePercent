package com.onepercent.goaltracker.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
        String uuid,
        @NotBlank(message = "Username is required")
        String username,
        String externalId
){
}