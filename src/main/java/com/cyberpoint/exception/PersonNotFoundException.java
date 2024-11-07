package com.cyberpoint.exception;

public class PersonNotFoundException extends NotFoundException {

  public PersonNotFoundException() {

    super("Usuário não encontrado(a)!");
  }
}
