package com.example.todo_app.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleInValidMethodArgs(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body("E");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgs(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body("Some Arguments are missing or Wrong. Try Again");
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<String> handleWrongForm(HttpMessageConversionException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
        return ResponseEntity.internalServerError().body("Something went wrong Try Again Later");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleNotReadableRequest(HttpMessageNotReadableException ex){
        return ResponseEntity.badRequest().body("Missing Argument");
    }



}
