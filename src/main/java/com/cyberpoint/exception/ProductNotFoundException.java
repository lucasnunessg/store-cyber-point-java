package com.cyberpoint.exception;

public class ProductNotFoundException extends NotFoundException {

  public ProductNotFoundException() {

    super("Produto não encontrado!");
  }
}
