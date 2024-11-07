package com.cyberpoint.dto;

import com.cyberpoint.entity.Product;
import com.cyberpoint.entity.SaleItem;

public record SaleItemCreateDto(Long quantify, Long productId) {

  public SaleItem toEntity(Product product) {
    return new SaleItem(quantify, product);
  }
}
