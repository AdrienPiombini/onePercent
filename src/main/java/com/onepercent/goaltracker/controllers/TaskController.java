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
        var taskListDto = taskList.getData().stream().map(taskMapper::toDto).toList();
        return ResponseEntity.ok(taskListDto);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("taskId") UUID taskId){
        var task = taskService.getTask(taskId);
        var taskDto = taskMapper.toDto(task.getData());
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDto taskDto){
        var task = taskMapper.fromDto(taskDto);
        var result = taskService.createTask(task);
        return ResponseEntity.status(201).body(result.getData());
    }

    @PostMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable("taskId") UUID uuid, @RequestBody TaskDto taskDto){
        var task = taskMapper.fromDto(taskDto);
        var result = taskService.updateTask(uuid, task);
        return ResponseEntity.status(202).body(result.getData());
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") UUID uuid){
        var result = taskService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
