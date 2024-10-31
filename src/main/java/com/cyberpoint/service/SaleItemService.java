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

  public SaleItem findiById(Long id) {
    Optional<SaleItem> saleItem = saleItemRepository.findById(id);

    if(saleItem.isEmpty()) {
      throw new SaleNotFoundException("Produto não encontrado!");
    }

    return saleItem.get();
  }


}
