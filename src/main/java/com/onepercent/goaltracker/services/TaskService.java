package com.onepercent.goaltracker.services;

import com.onepercent.goaltracker.utils.ServiceResult;
import com.onepercent.goaltracker.domain.entities.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    ServiceResult<List<Task>> getAllTasks();
    ServiceResult<Task> getTask(UUID uuid);
    ServiceResult<?> createTask(Task task);
    ServiceResult<?> delete(UUID uuid);
    ServiceResult<?> updateTask(UUID uuid, Task task);
}
