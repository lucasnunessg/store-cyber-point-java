package com.cyberpoint.exception;

public class EmailDuplicateException extends RuntimeException {

  public EmailDuplicateException(String message) {
    super(message);
  }
}
