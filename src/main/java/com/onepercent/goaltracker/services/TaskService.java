package com.onepercent.goaltracker.services;

import com.onepercent.goaltracker.domain.entities.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTask(UUID uuid);
    void createTask(Task task);
    void delete(UUID uuid);
    void updateTask(UUID uuid, Task task);
}
