package com.onepercent.goaltracker.controllers;


import com.onepercent.goaltracker.domain.dto.TaskDto;
import com.onepercent.goaltracker.domain.entities.Task;
import com.onepercent.goaltracker.mappers.TaskMapper;
import com.onepercent.goaltracker.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "goals/{goal_id}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(final TaskService taskService, TaskMapper taskMapper){
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        var taskList = taskService.getAllTasks();
        var taskListDto = taskList.stream().map(taskMapper::toDto).toList();
        return ResponseEntity.ok(taskListDto);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("taskId") UUID taskId){
        var task = taskService.getTask(taskId);
        var taskDto = taskMapper.toDto(task);
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskDto taskDto){
        var task = taskMapper.fromDto(taskDto);
        taskService.createTask(task);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable("taskId") UUID uuid, @RequestBody TaskDto taskDto){
        var task = taskMapper.fromDto(taskDto);
        taskService.updateTask(uuid, task);
        return ResponseEntity.status(202).build();
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") UUID uuid){
        taskService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
