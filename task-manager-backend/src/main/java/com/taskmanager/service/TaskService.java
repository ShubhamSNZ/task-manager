package com.taskmanager.service;

import java.util.List;

import com.taskmanager.dto.request.TaskRequest;
import com.taskmanager.dto.request.TaskUpdateRequest;
import com.taskmanager.dto.response.TaskResponse;
import com.taskmanager.entity.TaskStatus;

public interface TaskService {
	TaskResponse createTask(TaskRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(int id);

    TaskResponse updateTask(int id, TaskUpdateRequest request);

    void deleteTask(int id);

    List<TaskResponse> searchTasks(String title);

    List<TaskResponse> filterByStatus(TaskStatus status);
    
    List<TaskResponse> filterByCategory(String category);
}
