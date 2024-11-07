package com.cyberpoint.dto;

import com.cyberpoint.entity.Sales;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record SalesDto(Long id, LocalDateTime saleDate, PersonDto personDto, List<SaleItemDto> saleItems) {

  public static SalesDto fromEntity(Sales sales) {
    PersonDto personDto =
        sales.getPerson() != null ? PersonDto.fromEntity(sales.getPerson()) : null;
    List<SaleItemDto> saleItems = (sales.getSaleItems() != null) ?
        sales.getSaleItems().stream()
            .map(SaleItemDto::fromEntity)
            .collect(Collectors.toList())
        : List.of(); // Usando List.of() para garantir uma lista vazia
    return new SalesDto(
        sales.getId(),
        sales.getSaleDate(),
        personDto,
        saleItems
    );

  }

}
