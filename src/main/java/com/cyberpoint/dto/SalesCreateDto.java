package com.cyberpoint.dto;

import com.cyberpoint.entity.Sales;
import java.time.LocalDateTime;
import java.util.List;

public record SalesCreateDto(LocalDateTime saleDate, List<SaleItemCreateDto> saleItems) {

  public Sales toEntity() {
    return new Sales(saleDate != null ? saleDate : LocalDateTime.now());
  }
}
