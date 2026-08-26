package com.example.todo_app.exception;

import com.example.todo_app.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
        ErrorResponseDto res = new ErrorResponseDto(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),
                    ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleInValidMethodArgs(MethodArgumentNotValidException ex,HttpServletRequest request){
        ErrorResponseDto res = new ErrorResponseDto(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation error",request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgs(IllegalArgumentException ex,HttpServletRequest request){
        ErrorResponseDto res = new ErrorResponseDto(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "ReCheck Arguments and Try Again",request.getRequestURI());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ErrorResponseDto> handleWrongForm(HttpMessageConversionException ex,HttpServletRequest request){
        ErrorResponseDto res = new ErrorResponseDto(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Some Arguments are missing or wrong . Try Again Later", request.getRequestURI());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex,HttpServletRequest request){
        ErrorResponseDto res = new ErrorResponseDto(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Something went wrong Try Again Later",request.getRequestURI());
        return ResponseEntity.internalServerError().body(res);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleNotReadableRequest(HttpMessageNotReadableException ex,HttpServletRequest request ){
        ErrorResponseDto res = new ErrorResponseDto(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Some Arguments are missing or Wrong. Try Again",request.getRequestURI());
        return ResponseEntity.badRequest().body(res);
    }



}
