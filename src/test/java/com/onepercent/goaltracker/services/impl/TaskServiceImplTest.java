package com.onepercent.goaltracker.services.impl;

import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.domain.entities.TaskStatus;
import com.onepercent.goaltracker.repositories.GoalRepository;
import com.onepercent.goaltracker.repositories.TaskRepository;
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
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    GoalRepository goalRepository;

    @InjectMocks
    TaskServiceImpl taskService;

    private final Task task  = Task.builder()
            .id(UUID.randomUUID())
            .description("new task")
            .title("new title task")
            .status(TaskStatus.TODO)
            .goalId(UUID.randomUUID())
            .build();

    @Test
    void getAllTask() {
        var result = taskService.getAllTasks();
        assertTrue(result.isSuccess());
        assertTrue(result.getData().isEmpty());
    }

    @Test
    void shouldNotGetTask() {
        var fakeId = UUID.randomUUID();

        when(taskRepository.findById(fakeId)).thenReturn(Optional.empty());
        var result = taskService.getTask(fakeId);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldGetTask() {
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        var result = taskService.getTask(task.getId());

        assertTrue(result.isSuccess());
        assertEquals(result.getData(), task);
    }

    @Test
    void shouldNotCreated() {
        var result = taskService.createTask(task);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldCreated() {
        when(goalRepository.existsById(task.getGoalId())).thenReturn(true);
        var result = taskService.createTask(task);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldDeleted() {
        var result = taskService.delete(task.getId());
        assertTrue(result.isSuccess());
    }
    
    @Test
    void shouldNotUpdated(){
        when(taskRepository.findById(task.getId())).thenReturn(Optional.empty());
        var result = taskService.updateTask(task.getId(), task);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldUpdated() {
        var updatedTask = Task.builder()
                .id(task.getId())
                .title("updatedTile")
                .build();

        when(taskRepository.findById(updatedTask.getId())).thenReturn(Optional.of(task));


        var result = taskService.updateTask(task.getId(), task);

        assertTrue(result.isSuccess());
    }
}