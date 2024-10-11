package com.cyberpoint.controller.advice;

import com.cyberpoint.exception.PersonNotFoundException;
import com.cyberpoint.exception.ProductDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.cyberpoint.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler({ProductNotFoundException.class, PersonNotFoundException.class})
  public ResponseEntity<String> handleNotFound(RuntimeException ex) {
    String message;

    if(ex instanceof ProductNotFoundException) {
      message = "Produto não encontrado!";
    } else if (ex instanceof PersonNotFoundException) {
      message = "Usuário não encontrado!";
      
    }else {
      message = "Erro interno";
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(message);
  }

  @ExceptionHandler({ProductDuplicateException.class})
  public ResponseEntity<String> handleDuplicate(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());

  }
}
