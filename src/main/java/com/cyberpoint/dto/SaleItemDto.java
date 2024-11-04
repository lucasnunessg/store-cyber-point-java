package com.cyberpoint.dto;

import com.cyberpoint.entity.Product;
import com.cyberpoint.entity.SaleItem;

public record SaleItemDto(Long id, Long quantify, ProductDto productDto) {

  public static SaleItemDto fromEntity(SaleItem saleItem) {
    ProductDto productDto =
        saleItem.getSale() != null ? ProductDto.fromEntity(saleItem.getProduct()) : null;
    return new SaleItemDto(
        saleItem.getId(),
        saleItem.getQuantify(),
        productDto
    );
  }
}
