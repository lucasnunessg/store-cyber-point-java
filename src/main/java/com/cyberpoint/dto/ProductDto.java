package com.cyberpoint.dto;

import com.cyberpoint.entity.Product;

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
