package com.cyberpoint.controller.advice;

import com.cyberpoint.exception.ProductDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.cyberpoint.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler({ProductNotFoundException.class})
  public ResponseEntity<String> handleProductNotFound(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
  }

  @ExceptionHandler({ProductDuplicateException.class})
  public ResponseEntity<String> handleDuplicate(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());

  }
}
