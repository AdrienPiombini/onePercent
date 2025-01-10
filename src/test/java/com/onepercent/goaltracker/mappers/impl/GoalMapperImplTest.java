package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.GoalDto;
import com.onepercent.goaltracker.domain.entities.Goal;
import com.onepercent.goaltracker.domain.entities.User;
import com.onepercent.goaltracker.mappers.GoalMapper;
import com.onepercent.goaltracker.mappers.TaskMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GoalMapperImplTest {

    private final GoalMapperImpl goalMapper = new GoalMapperImpl();

    @Test
    void fromGoalDto() {
        GoalDto goalDto = new GoalDto("6bda75f5-8fe8-4be5-8aaf-4dbe7fd1a778", "Gain muscle", "Bench 100kg", "Fake UserID");

        Goal goal = goalMapper.fromGoalDto(goalDto);

        assertThat(goal.getId()).isEqualTo(goalDto.id());
        assertThat(goal.getTitle()).isEqualTo(goalDto.title());
        assertThat(goal.getDescription()).isEqualTo(goalDto.description());
        assertThat(goal.getUserId()).isEqualTo(goalDto.userId());
    }

    @Test
    void toGoalDto() {
    }
}