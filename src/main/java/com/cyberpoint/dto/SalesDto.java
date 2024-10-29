package com.cyberpoint.dto;

import com.cyberpoint.entity.Sales;
import java.time.LocalDateTime;

public record SalesDto(Long id, LocalDateTime saleDate) {
  public static SalesDto fromEntity(Sales sales) {
  return new SalesDto(
      sales.getId(),
      sales.getSaleDate()
  );

  }

}
