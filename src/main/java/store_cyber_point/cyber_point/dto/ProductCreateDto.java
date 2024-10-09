package store_cyber_point.cyber_point.dto;

import java.math.BigDecimal;
import store_cyber_point.cyber_point.Entity.Product;

public record ProductCreateDto(String name, Double price, String description, String image) {

  public Product toEntity() {
    return new Product(name, price, description, image);
  }

}
