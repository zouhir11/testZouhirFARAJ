package com.example.ecommerce.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> notFound(NotFoundException e){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
  }
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<?> badRequest(BadRequestException e){
    return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> validation(MethodArgumentNotValidException e){
    var msg = e.getBindingResult().getFieldErrors().stream()
      .map(f -> f.getField()+": "+f.getDefaultMessage()).toList();
    return ResponseEntity.badRequest().body(Map.of("errors", msg));
  }
}
