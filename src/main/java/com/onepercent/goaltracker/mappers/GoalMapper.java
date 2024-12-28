package com.onepercent.goaltracker.mappers;

import com.onepercent.goaltracker.domain.dto.GoalDto;
import com.onepercent.goaltracker.domain.entities.Goal;

public interface GoalMapper {

    Goal fromGoalDto(GoalDto goalDto);

    GoalDto toGoalDto(Goal goal);
}
