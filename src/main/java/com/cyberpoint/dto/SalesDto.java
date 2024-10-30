package com.cyberpoint.dto;

import com.cyberpoint.entity.Sales;
import java.time.LocalDateTime;

public record SalesDto(Long id, LocalDateTime saleDate, PersonDto personDto) {

  public static SalesDto fromEntity(Sales sales) {
    PersonDto personDto =
        sales.getPerson() != null ? PersonDto.fromEntity(sales.getPerson()) : null;
    return new SalesDto(
        sales.getId(),
        sales.getSaleDate(),
        personDto
    );

  }

}
