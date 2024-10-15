package com.cyberpoint.controller.advice;

import com.cyberpoint.exception.EmailDuplicateException;
import com.cyberpoint.exception.NotFoundException;
import com.cyberpoint.exception.PersonDuplicateException;
import com.cyberpoint.exception.PersonNotFoundException;
import com.cyberpoint.exception.ProductDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.cyberpoint.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler()
  public ResponseEntity<String> handleNotFound(NotFoundException ex) {


    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
  }

  @ExceptionHandler({ProductDuplicateException.class})
  public ResponseEntity<String> handleDuplicate(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());

  }

  @ExceptionHandler({PersonDuplicateException.class})
  public ResponseEntity<String> handleDuplicatePerson(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());

  }

  @ExceptionHandler({EmailDuplicateException.class})
  public ResponseEntity<String> handleDuplicateEmail(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());

  }

 /* @ExceptionHandler({ProductNotFoundException.class, PersonNotFoundException.class})
  public ResponseEntity<String> handleNotFound(RuntimeException ex) {

    // } else {
    //   message = "Erro interno";
    // }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
 }*/
}
