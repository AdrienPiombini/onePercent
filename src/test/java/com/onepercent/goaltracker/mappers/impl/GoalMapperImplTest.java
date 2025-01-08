package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.entities.User;
import com.onepercent.goaltracker.mappers.GoalMapper;
import com.onepercent.goaltracker.mappers.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GoalMapperImplTest {

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private GoalMapper goalMapper;

    @BeforeEach
    void setup(){
    }

    @Test
    void fromGoalDto() {
    }

    @Test
    void toGoalDto() {
    }
}