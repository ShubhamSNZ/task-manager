package com.taskmanager.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskmanager.dto.request.TaskRequest;
import com.taskmanager.dto.request.TaskUpdateRequest;
import com.taskmanager.dto.response.TaskResponse;
import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.TaskStatus;
import com.taskmanager.mapper.TaskMapper;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

	@Override
	public TaskResponse createTask(TaskRequest request) {
		Task task = taskMapper.toEntity(request);
        Task saved = taskRepository.save(task);
        return taskMapper.toResponse(saved);
	}

	@Override
	public List<TaskResponse> getAllTasks() {
		return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponse)
                .toList();
	}

	@Override
	public TaskResponse getTaskById(int id) {
		Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return taskMapper.toResponse(task);
	}

	@Override
	public TaskResponse updateTask(int id, TaskUpdateRequest request) {
	    Task existing = taskRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Task not found"));
	    if (request.getTitle() != null)
	        existing.setTitle(request.getTitle());
	    if (request.getDescription() != null)
	        existing.setDescription(request.getDescription());
	    if (request.getPriority() != null)
	        existing.setPriority(
	            TaskPriority.valueOf(request.getPriority().toUpperCase()));
	    if (request.getStatus() != null)
	        existing.setStatus(
	            TaskStatus.valueOf(request.getStatus().toUpperCase()));
	    if (request.getCategory() != null)
	        existing.setCategory(request.getCategory());
	    if (request.getDueDate() != null)
	        existing.setDueDate(request.getDueDate());
	    Task updated = taskRepository.save(existing);
	    return taskMapper.toResponse(updated);
	}

	@Override
	public void deleteTask(int id) {
		taskRepository.deleteById(id);
	}

	@Override
	public List<TaskResponse> searchTasks(String title) {
		return taskRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(taskMapper::toResponse)
                .toList();
	}

	@Override
	public List<TaskResponse> filterByStatus(TaskStatus status) {
		return taskRepository.findByStatus(status)
                .stream()
                .map(taskMapper::toResponse)
                .toList();
	}

	@Override
	public List<TaskResponse> filterByCategory(String category) {
		return taskRepository.findByCategoryContainingIgnoreCase(category)
				.stream()
				.map(taskMapper::toResponse)
				.toList();
	}

}
