package com.taskmanager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.dto.request.TaskRequest;
import com.taskmanager.dto.request.TaskUpdateRequest;
import com.taskmanager.dto.response.TaskResponse;
import com.taskmanager.entity.TaskStatus;
import com.taskmanager.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
	private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    
 // CREATE
    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }

    // GET ALL
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public TaskResponse updateTask(
            @PathVariable int id,
            @RequestBody TaskUpdateRequest request) {
    		System.out.println("PUT HIT");
        return taskService.updateTask(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }

    // SEARCH
    @GetMapping("/title")
    public List<TaskResponse> searchTasks(@RequestParam String title) {
        return taskService.searchTasks(title);
    }

    // FILTER BY STATUS
    @GetMapping("/status")
    public List<TaskResponse> filterByStatus(@RequestParam TaskStatus status) {
        return taskService.filterByStatus(status);
    }
    
    // FILTER BY CATEGORY
    @GetMapping("/category")
    public List<TaskResponse> filterByCategory(@RequestParam String category){
    	return taskService.filterByCategory(category);
    }
}
