package com.cyberpoint.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class LoginErrorException extends BadCredentialsException {

  public LoginErrorException(String message) {
    super(message);
  }
}
