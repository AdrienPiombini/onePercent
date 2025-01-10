package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.GoalDto;
import com.onepercent.goaltracker.domain.entities.Goal;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GoalMapperImplTest {

    private final GoalMapperImpl goalMapper = new GoalMapperImpl();

    @Test
    void fromGoalDto() {
        var goalDto = new GoalDto(UUID.randomUUID().toString(), "Gain muscle", "Bench 100kg", UUID.randomUUID().toString());

        var result = goalMapper.fromGoalDto(goalDto);

        assertThat(result.getId().toString()).isEqualTo(goalDto.id());
        assertThat(result.getTitle()).isEqualTo(goalDto.title());
        assertThat(result.getDescription()).isEqualTo(goalDto.description());
        assertThat(result.getUserId().toString()).isEqualTo(goalDto.userId());
    }

    @Test
    void toGoalDto() {
        var goal = Goal.builder().id(UUID.randomUUID())
                .title("Fake Title")
                .description("Fake Description")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .userId(UUID.randomUUID())
                .build();

        var result = goalMapper.toGoalDto(goal);

        assertThat(result.id()).isEqualTo(goal.getId().toString());
        assertThat(result.description()).isEqualTo(goal.getDescription());
        assertThat(result.title()).isEqualTo(goal.getTitle());
        assertThat(result.userId()).isEqualTo(goal.getUserId().toString());
    }
}