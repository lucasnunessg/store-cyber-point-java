package com.cyberpoint.dto;

import com.cyberpoint.entity.Product;
import com.cyberpoint.service.PersonService;

public record ProductDto(Long id, String name, Double price, String description, String category,
                         String image, PersonDto person) {

  public static ProductDto fromEntity(Product product) {

    PersonDto personDto = product.getPerson() != null ?
        PersonDto.fromEntity(product.getPerson()) : null;

    return new ProductDto(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getDescription(),
        product.getCategory(),
        product.getImage(),
        personDto
    );
  }

}
