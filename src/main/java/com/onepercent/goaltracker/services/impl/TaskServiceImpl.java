package com.onepercent.goaltracker.services.impl;

import com.onepercent.goaltracker.utils.ServiceResult;
import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.domain.entities.TaskStatus;
import com.onepercent.goaltracker.repositories.GoalRepository;
import com.onepercent.goaltracker.repositories.TaskRepository;
import com.onepercent.goaltracker.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final GoalRepository goalRepository;
    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    public TaskServiceImpl(final TaskRepository taskRepository, final GoalRepository goalRepository){
        this.taskRepository = taskRepository;
        this.goalRepository = goalRepository;
    }

    @Override
    public ServiceResult<List<Task>> getAllTasks() {
        var result = taskRepository.findAll();
        return ServiceResult.ok(result);
    }

    @Override
    public ServiceResult<Task> getTask(UUID uuid) {
        var task =  taskRepository.findById(uuid);
        return task.map(ServiceResult::ok)
                .orElse(ServiceResult.error(String.format("Task %s does not exist", uuid)));
    }

    @Override
    public ServiceResult<?> createTask(Task task) {
        var canCreateTask = canCreate(task);
        if( ! canCreateTask){
            return ServiceResult.error("Goal Id does not exist");
        }

        log.info("Creation of task {} for this user {}", task.getTitle(), task.getGoalId());
        if(task.getStatus() == null){
            task.setStatus(TaskStatus.TODO);
        }

        var result = taskRepository.save(task);
        return ServiceResult.ok(result);
    }

    @Override
    public ServiceResult<?> delete(UUID uuid) {
        log.info("Delete task id {}", uuid);
        taskRepository.deleteById(uuid);

        return ServiceResult.ok(null);
    }

    @Override
    public ServiceResult<Task> updateTask(UUID uuid, Task task) {
        var existentTask = taskRepository.findById(uuid);

        if(existentTask.isEmpty()){
            return ServiceResult.error(String.format("Task with id %s does not exist", task.getId()));
        }

        if(task.getStatus() == null){
            task.setStatus(existentTask.get().getStatus());
        }

        var result = taskRepository.save(task);

        return ServiceResult.ok(result);
    }

    private boolean canCreate(Task task) {
        assert(task.getGoalId() != null);
        return goalRepository.existsById(task.getGoalId());
    }
}
