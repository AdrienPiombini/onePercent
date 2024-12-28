package com.onepercent.goaltracker.domain.dto;

import java.util.List;
import java.util.UUID;

public record UserDto(UUID uuid, String username, List<GoalDto> goalDtoList) {
}
