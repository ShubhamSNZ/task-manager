package com.taskmanager.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
	@NotBlank(message = "Title is required")
    private String title;
    private String description;
    @NotBlank(message = "Priority is required")
    private String priority;
    private LocalDate dueDate;
    private String category;
    private String status;
}
