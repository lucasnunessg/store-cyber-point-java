package com.cyberpoint.service;

import com.cyberpoint.entity.SaleItem;
import com.cyberpoint.exception.SaleNotFoundException;
import com.cyberpoint.repository.SaleItemRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SaleItemService {

  SaleItemRepository saleItemRepository;

  @Autowired
  public SaleItemService(SaleItemRepository saleItemRepository) {
    this.saleItemRepository = saleItemRepository;
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


}
