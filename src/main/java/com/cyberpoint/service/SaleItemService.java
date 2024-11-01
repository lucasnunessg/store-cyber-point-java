package com.cyberpoint.service;

import com.cyberpoint.entity.Product;
import com.cyberpoint.entity.SaleItem;
import com.cyberpoint.entity.Sales;
import com.cyberpoint.exception.SaleNotFoundException;
import com.cyberpoint.repository.SaleItemRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SaleItemService {

  SaleItemRepository saleItemRepository;
  ProductService productService;
  SaleService saleService;

  @Autowired
  public SaleItemService(SaleItemRepository saleItemRepository, ProductService productService, SaleService saleService) {
    this.saleItemRepository = saleItemRepository;
    this.productService = productService;
    this.saleService = saleService;
  }

  public List<SaleItem> findAll() {
    return saleItemRepository.findAll();


  }

  public SaleItem findSaleItemById(Long id) {
    Optional<SaleItem> saleItem = saleItemRepository.findById(id);

    if(saleItem.isEmpty()) {
      throw new SaleNotFoundException("Produto não encontrado!");
    }

    return saleItem.get();
  }

  public SaleItem updateSaleItem(Long id, SaleItem saleItem) {
    SaleItem saleItemFromDb = findSaleItemById(id);

    saleItemFromDb.setSale(saleItem.getSale());
    saleItemFromDb.setQuantify(saleItem.getQuantify());
    saleItemFromDb.setProduct(saleItem.getProduct());

    return saleItemRepository.save(saleItemFromDb);
  }

  public SaleItem deleteById(Long id) {
    SaleItem sale = findSaleItemById(id);

    saleItemRepository.deleteById(id);
    return sale;

  }

  public SaleItem setSaleItemAndProduct(Long saleItemId, Long productId) {
    SaleItem saleItem = findSaleItemById(saleItemId);
    Product product = productService.findById(productId);

    saleItem.setProduct(product);

    return saleItemRepository.save(saleItem);
  }

  public SaleItem deleteSaleItemProduct(Long saleItemId) {
    SaleItem saleItem = findSaleItemById(saleItemId);

    saleItem.setProduct(null);

   return saleItemRepository.save(saleItem);
  }

  public SaleItem setSaleItemAndSale(Long saleItemId, Long saleId) {
    SaleItem saleItem = findSaleItemById(saleItemId);
    Sales sales = saleService.findSaleById(saleId);

    saleItem.setSale(sales);

    return saleItemRepository.save(saleItem);
  }

}
