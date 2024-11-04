package com.cyberpoint.dto;

import com.cyberpoint.entity.SaleItem;

public record SaleItemCreateDto(Long quantify) {

  public SaleItem toEntity() {
    return new SaleItem(quantify);
  }
}
