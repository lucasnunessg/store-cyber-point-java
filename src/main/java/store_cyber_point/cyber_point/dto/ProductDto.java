package store_cyber_point.cyber_point.dto;

import java.math.BigDecimal;
import store_cyber_point.cyber_point.Entity.Product;

public record ProductDto(Long id, String name, Double price, String description, String image) {

  public static ProductDto fromEntity(Product product) {
    return new ProductDto(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getDescription(),
        product.getImage()
    );
  }

}
