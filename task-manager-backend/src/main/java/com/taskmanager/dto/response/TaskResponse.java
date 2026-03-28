package com.taskmanager.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
	private int id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    private String category;
    private LocalDate createdAt;
}
