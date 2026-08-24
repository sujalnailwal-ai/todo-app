package com.example.todo_app.repository;

import com.example.todo_app.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface ToDoRepository extends JpaRepository<ToDo, Long> {


}
