package com.example.todo_app.dto;

import com.example.todo_app.type.TaskStatus;
import lombok.Data;

@Data
public class ToDoUpdateDto {

    private String title;
    private String description;
    private TaskStatus status;
}
