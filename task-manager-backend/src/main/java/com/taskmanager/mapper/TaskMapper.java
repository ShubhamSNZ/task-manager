package com.taskmanager.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.taskmanager.dto.request.TaskRequest;
import com.taskmanager.dto.response.TaskResponse;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.TaskStatus;

@Component
public class TaskMapper {
	public Task toEntity(TaskRequest request) {
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(TaskPriority.valueOf(request.getPriority().toUpperCase()));
        task.setDueDate(request.getDueDate());
        task.setCategory(request.getCategory());

        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDate.now());

        return task;
    }

    public TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus().name());
        response.setPriority(task.getPriority().name());
        response.setDueDate(task.getDueDate());
        response.setCategory(task.getCategory());
        response.setCreatedAt(task.getCreatedAt());

        return response;
    }
}
