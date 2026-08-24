package com.example.todo_app.controller;

import com.example.todo_app.dto.ToDoRequestDto;
import com.example.todo_app.dto.ToDoResponseDto;
import com.example.todo_app.dto.ToDoUpdateDto;
import com.example.todo_app.service.ToDoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;


    @GetMapping
    public ResponseEntity<List<ToDoResponseDto>> getAllTodoList(){
        List<ToDoResponseDto> taskList=toDoService.getAll();
        return ResponseEntity.ok(taskList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> findTaskWithID(@PathVariable("id") Long id){
        ToDoResponseDto task = toDoService.getTaskWithId(id);
        return ResponseEntity.ok(task);
    }


    @PostMapping
    public ResponseEntity<Void> addTask(@Valid @RequestBody ToDoRequestDto task){
        toDoService.addNewTask(task);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        toDoService.deleteTaskWithId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTheTask(@PathVariable("id") Long id, @Valid @RequestBody ToDoRequestDto toDoRequestDto){
     toDoService.updateTaskWithId(id,toDoRequestDto);
     return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> editTaskfields(@PathVariable("id") Long id,@RequestBody ToDoUpdateDto toDoUpdateDto){
        toDoService.editFieldsInTask(id,toDoUpdateDto);
        return ResponseEntity.ok().build();
    }



}
