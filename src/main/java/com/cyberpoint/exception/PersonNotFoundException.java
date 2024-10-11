package com.cyberpoint.exception;

public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException() {

    super("Usuário não encontrado(a)!");
  }
}
