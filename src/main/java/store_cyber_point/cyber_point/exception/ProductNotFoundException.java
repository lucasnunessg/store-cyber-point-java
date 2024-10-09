package store_cyber_point.cyber_point.exception;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException() {

    super("Produto não encontrado!");
  }
}
