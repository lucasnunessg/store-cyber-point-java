package com.cyberpoint.exception;

public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException(String message) {

    super("Usuário não encontrado(a)!");
  }
}
