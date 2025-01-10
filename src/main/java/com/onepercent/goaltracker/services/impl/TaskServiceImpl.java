package com.onepercent.goaltracker.services.impl;

import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.domain.entities.TaskStatus;
import com.onepercent.goaltracker.repositories.TaskRepository;
import com.onepercent.goaltracker.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    public TaskServiceImpl(final TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(UUID uuid) {
        var task =  taskRepository.findById(uuid);
        if(task.isEmpty()) throw new NullPointerException(String.format("Task %s does not exist", uuid));
        return task.get();
    }

    @Override
    public void createTask(Task task) {
        log.info("Creation of task {} for this user {}", task.getTitle(), task.getGoalId());
        if(task.getStatus() == null){
            task.setStatus(TaskStatus.TODO);
        }
        taskRepository.save(task);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("Delete task id {}", uuid);
        taskRepository.deleteById(uuid);
    }

    @Override
    public void updateTask(UUID uuid, Task task) {
        var existentTask = taskRepository.findById(uuid);
        if(existentTask.isEmpty()){
            throw new NullPointerException(String.format("Task with id %s does not exist", task.getId()));
        }

        if(task.getStatus() == null){
            task.setStatus(existentTask.get().getStatus());
        }
        taskRepository.save(task);
    }
}
