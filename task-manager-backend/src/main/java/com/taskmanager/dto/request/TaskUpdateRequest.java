package com.taskmanager.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskUpdateRequest {
	private String title;
    private String description;
    private String priority;
    private LocalDate dueDate;
    private String category;
    private String status;
}
