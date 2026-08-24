package com.example.todo_app.dto;

import com.example.todo_app.type.TaskStatus;
import lombok.Data;
import tools.jackson.databind.ext.javatime.deser.key.LocalDateKeyDeserializer;

import java.time.LocalDateTime;

@Data
public class ToDoResponseDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;

}
