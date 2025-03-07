package com.onepercent.goaltracker.services.impl;

import com.onepercent.goaltracker.domain.entities.Goal;
import com.onepercent.goaltracker.repositories.GoalRepository;
import com.onepercent.goaltracker.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoalServiceImplTest {
    @Mock
    private GoalRepository goalRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GoalServiceImpl goalService;

    private final Goal goal = Goal.builder()
            .id(UUID.randomUUID())
            .title("fake Title")
            .description("fake description")
            .userId(UUID.randomUUID())
            .build();

    @Test
    void shouldGetAllGoals(){
        var result = goalService.getAllGoals();

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotGetGoal(){
        var fakeId = UUID.randomUUID();

        when(goalRepository.findById(fakeId)).thenReturn(Optional.empty());
        var result = goalService.getGoal(fakeId);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldGetGoal(){
        when(goalRepository.findById(goal.getId())).thenReturn(Optional.of(goal));
        var result = goalService.getGoal(goal.getId());

        assertTrue(result.isSuccess());
        assertEquals(result.getData(), goal);
    }

    @Test
    void shouldNotCreateGoalWithWrongUserId(){
        var fakeUserId = UUID.randomUUID();
        var newGoal = Goal.builder().userId(fakeUserId).build();

        when(userRepository.existsById(fakeUserId)).thenReturn(false);
        var result = goalService.createGoal(newGoal);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotCreateGoalWithExistentGoalId(){

        var result = goalService.createGoal(goal);

        assertFalse(result.isSuccess());
    }


    @Test
    void shouldCreateGoal(){
        var validGoal = Goal.builder().userId(UUID.randomUUID()).build();
        when(userRepository.existsById(validGoal.getUserId())).thenReturn(true);
        var result = goalService.createGoal(validGoal);

        assertTrue(result.isSuccess());
    }


    @Test
    void shouldNotUpdate(){
        var result = goalService.updateGoal(goal.getId(), goal);

        assertFalse(result.isSuccess());
    }


    @Test
    void shouldUpdate(){
        when(goalRepository.existsById(goal.getId())).thenReturn(true);
        var result = goalService.updateGoal(goal.getId(), goal);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldDelete(){
        var result = goalService.deleteGoal(goal.getId());
        assertTrue(result.isSuccess());
    }
}