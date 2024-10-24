package com.cyberpoint.controller.advice;

import com.cyberpoint.exception.EmailDuplicateException;
import com.cyberpoint.exception.FieldsEmptyException;
import com.cyberpoint.exception.LoginErrorException;
import com.cyberpoint.exception.NotFoundException;
import com.cyberpoint.exception.PersonDuplicateException;
import com.cyberpoint.exception.PersonNotFoundException;
import com.cyberpoint.exception.ProductDuplicateException;
import com.cyberpoint.exception.UpdatePersonException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ExceptionHandler({LoginErrorException.class})
  public ResponseEntity<String> handleLoginFailed(BadCredentialsException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(ex.getMessage());
  }

  @ExceptionHandler({UpdatePersonException.class})
  public ResponseEntity<String> handleUpdateFailed(BadCredentialsException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        errors.put(error.getField(), error.getDefaultMessage())
    );
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

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
