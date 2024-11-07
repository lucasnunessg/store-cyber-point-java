package com.cyberpoint.exception;

public class PersonDuplicateException extends RuntimeException {

  public PersonDuplicateException(String message) {
    super(message);
  }
}
