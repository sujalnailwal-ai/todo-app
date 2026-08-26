package com.example.todo_app.service;


import com.example.todo_app.dto.ToDoRequestDto;
import com.example.todo_app.dto.ToDoResponseDto;
import com.example.todo_app.dto.ToDoUpdateDto;
import com.example.todo_app.entity.ToDo;
import com.example.todo_app.exception.ResourceNotFoundException;
import com.example.todo_app.repository.ToDoRepository;
import com.example.todo_app.type.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ToDoService {


    private final ToDoRepository toDoRepository;
    private final ModelMapper modelMapper;




    public List<ToDoResponseDto> getAll(){
        List<ToDo> task=toDoRepository.findAll();
        if(task.isEmpty()){
            throw new ResourceNotFoundException("No Task Found");
        }
        List<ToDoResponseDto> listOfTask = new ArrayList<>();
        for(ToDo toDo:task){
            listOfTask.add(modelMapper.map(toDo, ToDoResponseDto.class));
        }
        return listOfTask;
    }




    public ToDoResponseDto getTaskWithId(Long id){
        ToDo task= toDoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Task with id :"+id+" not found"));
        return modelMapper.map(task, ToDoResponseDto.class);
    }




    public void addNewTask(ToDoRequestDto task){
        ToDo newTask = modelMapper.map(task,ToDo.class);
        newTask.setStatus(TaskStatus.NOT_STARTED);
        newTask.setCreatedAt(LocalDateTime.now());
        newTask.setUpdatedAt(LocalDateTime.now());
        toDoRepository.save(newTask);
    }




    public void deleteTaskWithId(Long id){
        ToDo task = toDoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Task with id :"+id+" not found"));
        toDoRepository.delete(task);
    }




    public void updateTaskWithId(Long id,ToDoRequestDto toDoRequestDto){
        ToDo task = toDoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Task with id :"+id+" not found"));
        modelMapper.map(toDoRequestDto,task);
        task.setUpdatedAt(LocalDateTime.now());
        toDoRepository.save(task);
    }




    public void editFieldsInTask(Long id, ToDoUpdateDto updateDto){
        ToDo task = toDoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Task with id :"+id+" not found"));


        if(updateDto.getTitle()!=null){
            if(updateDto.getTitle().isBlank()){
                throw new IllegalArgumentException();
            }
            task.setTitle(updateDto.getTitle());
        }
        if(updateDto.getStatus()!=null){
            task.setStatus(updateDto.getStatus());
        }
        if(updateDto.getDescription()!=null){
            task.setDescription(updateDto.getDescription());
        }


        task.setUpdatedAt(LocalDateTime.now());
        toDoRepository.save(task);

    }






}
