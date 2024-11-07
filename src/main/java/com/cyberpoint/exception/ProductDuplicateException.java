package com.cyberpoint.exception;

import java.sql.SQLIntegrityConstraintViolationException;

public class ProductDuplicateException extends RuntimeException {

  public ProductDuplicateException() {
    super("Produto duplicado!");

  }
}
