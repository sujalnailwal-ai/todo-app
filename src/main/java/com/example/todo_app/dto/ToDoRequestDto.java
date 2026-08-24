package com.example.todo_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ToDoRequestDto {
    @NotBlank(message = "Title Cannot Be Blank")
    private String title;
    @Size(min = 5,max = 1000)
    private String description;
}
