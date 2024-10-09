package store_cyber_point.cyber_point.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import store_cyber_point.cyber_point.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler({ProductNotFoundException.class})
  public ResponseEntity<String> handleProductNotFound(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
  }
}
