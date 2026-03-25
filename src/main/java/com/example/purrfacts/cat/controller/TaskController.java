package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.model.Cartoon;
import com.example.purrfacts.cat.model.Task;
import com.example.purrfacts.cat.repository.CartoonRepository;
import com.example.purrfacts.cat.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        var tasks = taskRepository.getAll();
        return ResponseEntity.ok(tasks);
    }
}
