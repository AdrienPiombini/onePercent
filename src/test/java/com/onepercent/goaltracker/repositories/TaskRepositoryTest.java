package com.onepercent.goaltracker.repositories;

import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.domain.entities.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    private Task task;


    @BeforeEach
    void setup(){
        task = Task.builder()
                .title("Wacth BJJ")
                .status(TaskStatus.TODO)
                .goalId(UUID.randomUUID())
                .description("John Danaher Arm bar Tutorial")
                .build();

        taskRepository.save(task);
    }

    @Test
    void findAll(){
        var result = taskRepository.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void findOne(){
        var foundTask = taskRepository.findById(task.getId());
        assertTrue(foundTask.isPresent());

        var existedTask = taskRepository.existsById(task.getId());
        assertTrue(existedTask);
    }

    @Test
    void updatedTask(){
        var newTitle = "Studying Arm bar";
        task.setTitle(newTitle);
        var result = taskRepository.save(task);
        assertEquals(newTitle, result.getTitle());
    }

    @Test
    void deletedTask(){
        taskRepository.deleteById(task.getId());
        var result = taskRepository.existsById(task.getId());
        assertFalse(result);
    }
}