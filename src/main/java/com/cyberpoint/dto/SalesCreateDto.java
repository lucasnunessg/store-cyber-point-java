package com.cyberpoint.dto;

import com.cyberpoint.entity.Person;
import com.cyberpoint.entity.Sales;
import java.time.LocalDateTime;

public record SalesCreateDto(LocalDateTime saleDate) {

  public Sales toEntity() {
    return new Sales(saleDate != null ? saleDate : LocalDateTime.now());
  }
}

