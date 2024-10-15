package com.cyberpoint.dto;

import com.cyberpoint.entity.Product;

public record ProductCreateDto(String name, Double price, String description, String category, String image) {

  public Product toEntity() {
    return new Product(name, price, description, category, image);
  }

}
