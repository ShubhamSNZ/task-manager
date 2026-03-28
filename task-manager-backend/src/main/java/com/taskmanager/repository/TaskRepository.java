package com.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskmanager.entity.Task;
import com.taskmanager.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	List<Task> findByStatus(TaskStatus status);

    List<Task> findByPriority(String priority);

    List<Task> findByTitleContainingIgnoreCase(String title);
    
    List<Task> findByCategoryContainingIgnoreCase(String category);
}
