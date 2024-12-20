package com.cyberpoint.controller;

import com.cyberpoint.dto.SaleItemCreateDto;
import com.cyberpoint.dto.SaleItemDto;
import com.cyberpoint.dto.SalesCreateDto;
import com.cyberpoint.dto.SalesDto;
import com.cyberpoint.entity.Product;
import com.cyberpoint.entity.SaleItem;
import com.cyberpoint.entity.Sales;
import com.cyberpoint.exception.ProductNotFoundException;
import com.cyberpoint.exception.SaleNotFoundException;
import com.cyberpoint.service.ProductService;
import com.cyberpoint.service.SaleItemService;
import com.cyberpoint.service.SaleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale-product")
public class SaleItemController {

  private final SaleItemService saleItemService;
  private final SaleService saleService;
  private final ProductService productService;

  @Autowired
  public SaleItemController(SaleItemService saleItemService, SaleService saleService,
      ProductService productService) {
    this.saleItemService = saleItemService;
    this.saleService = saleService;
    this.productService = productService;
  }

  @GetMapping
  public List<SaleItemDto> getAllSaleI() {
    List<SaleItem> allSaleI = saleItemService.findAll();
    return allSaleI.stream().map(SaleItemDto::fromEntity)
        .toList();
  }

  @GetMapping("/{id}")
  public SaleItemDto getSaleIById(@PathVariable Long id) {
    return SaleItemDto.fromEntity(saleItemService.findSaleItemById(id));
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSaleI(@PathVariable Long id) {
    saleItemService.deleteById(id);
    return ResponseEntity.ok("Deletado com sucesso!");
  }

  @PostMapping("/sales/{saleId}/product/{productId}")
  public SaleItemDto createSaleIProduct(@PathVariable Long saleId, @PathVariable Long productId,
      @RequestBody SaleItemCreateDto saleItemCreateDto) {

    SaleItem saleItem = saleItemService.createSaleItemWithProduct(saleId, productId,
        saleItemCreateDto.quantify());

    return SaleItemDto.fromEntity(saleItem);

  }

}