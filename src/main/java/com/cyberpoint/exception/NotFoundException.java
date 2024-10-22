package com.cyberpoint.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException() {
    super(
        "Não encontrado!"); //se nao tiver parametro aqui, todas as classes filhas que nao tiverem tb, pegam dessa mensagem.
  }

  public NotFoundException(String message) {
    super(message);
  }
}
