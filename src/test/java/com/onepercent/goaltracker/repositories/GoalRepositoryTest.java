package com.onepercent.goaltracker.repositories;

import com.onepercent.goaltracker.domain.entities.Goal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GoalRepositoryTest {
    @Autowired
    private GoalRepository goalRepository;

    private Goal goal;

    @BeforeEach
    void setup(){
        goal = Goal.builder()
                .title("Perde du poids")
                .userId(UUID.randomUUID())
                .description("perdre 34 kilos")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();

        goalRepository.save(goal);

    }

    @Test
    void findAll(){
        var result = goalRepository.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

    }
    @Test
    void findOne(){
        var result = goalRepository.findById(goal.getId());
        assertTrue(result.isPresent());
        var existedGoal = goalRepository.existsById(goal.getId());
        assertTrue(existedGoal);
    }

    @Test
    void updatedGoal(){
        var newTitle = "Gain Muscle";
        goal.setTitle(newTitle);
        var result = goalRepository.save(goal);
        assertEquals(newTitle, result.getTitle());
    }

    @Test
    void deleteGoal(){
        goalRepository.deleteById(goal.getId());
        var result = goalRepository.existsById(goal.getId());
        assertFalse(result);
    }


    // DOES NOT WORK  NEED TO INVESTIGATE WHY
    // THE 3 FOLLOWING TEST DOES NOT RESPECT VALIDATED SCHEMA SET IN ENTITIES CLASSES
    @Test
    void shouldNotInsertWithoutTitle(){
        var goalWithoutTitle = Goal.builder()
                .description("Fake Description")
                .id(UUID.randomUUID())
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> goalRepository.save(goalWithoutTitle));

    }

    @Test
    void shouldNotInsertWithoutUserId(){
        var goalWithoutUserId = Goal.builder()
                .title("Fake title")
                .build();

        assertThrows(
                DataIntegrityViolationException.class,
                () -> goalRepository.save(goalWithoutUserId)
        );

    }

    @Test
    void shouldNotUpdateId(){
        goal.setId(UUID.randomUUID());
        assertThrows(
                DataIntegrityViolationException.class,
                () -> goalRepository.save(goal)
        );
    }
}