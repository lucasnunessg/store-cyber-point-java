package com.cyberpoint.dto;

import com.cyberpoint.entity.Product;
import com.cyberpoint.service.PersonService;

public record ProductDto(Long id, String name, Double price, String description, String category,
                         String image) {

  public static ProductDto fromEntity(Product product) {

    return new ProductDto(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getDescription(),
        product.getCategory(),
        product.getImage()
    );
  }

}
