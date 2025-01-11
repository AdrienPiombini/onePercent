package com.onepercent.goaltracker.repositories;

import com.onepercent.goaltracker.domain.entities.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GoalRepositoryTest {
    @Autowired
    private GoalRepository goalRepository;

    private Goal goal;

    @Test
    void CRUD(){
        var result = goalRepository.findAll();
        assertTrue(result.isEmpty());

        goal = Goal.builder()
                .title("Perde du poids")
                .userId(UUID.randomUUID())
                .description("perdre 34 kilos")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();

        goalRepository.save(goal);

        var result0 = goalRepository.findAll();
        assertFalse(result0.isEmpty());

        var result1 = goalRepository.findById(goal.getId());
        assertTrue(result1.isPresent());

        var result2 = goalRepository.existsById(goal.getId());
        assertTrue(result2);

        goalRepository.deleteById(goal.getId());

        var result3 = goalRepository.existsById(goal.getId());
        assertFalse(result3);

    }
}