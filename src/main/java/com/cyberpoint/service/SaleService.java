package com.cyberpoint.service;

import com.cyberpoint.dto.SaleItemCreateDto;
import com.cyberpoint.dto.SaleItemDto;
import com.cyberpoint.dto.SalesCreateDto;
import com.cyberpoint.entity.Person;
import com.cyberpoint.entity.Product;
import com.cyberpoint.entity.SaleItem;
import com.cyberpoint.entity.Sales;
import com.cyberpoint.exception.PersonNotFoundException;
import com.cyberpoint.exception.ProductNotFoundException;
import com.cyberpoint.exception.SaleNotFoundException;
import com.cyberpoint.repository.PersonRepository;
import com.cyberpoint.repository.ProductRepository;
import com.cyberpoint.repository.SaleItemRepository;
import com.cyberpoint.repository.SalesRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

  SalesRepository salesRepository;
  PersonService personService;
  TokenService tokenService;
  PersonRepository personRepository;
  ProductRepository productRepository;
  SaleItemRepository saleItemRepository;

  @Autowired
  public SaleService(SalesRepository salesRepository, PersonService personService,
      TokenService tokenService,
      PersonRepository personRepository, ProductRepository productRepository,
      SaleItemRepository saleItemRepository) {
    this.salesRepository = salesRepository;
    this.personService = personService;
    this.tokenService = tokenService;
    this.personRepository = personRepository;
    this.productRepository = productRepository;
    this.saleItemRepository = saleItemRepository;

  }

  public List<Sales> findAll() {
    return salesRepository.findAll();
  }

  public Sales findSaleById(Long id) {
    Optional<Sales> sale = salesRepository.findById(id);

    if (sale.isEmpty()) {
      throw new SaleNotFoundException("Não encontrado!");
    }

    return sale.get();
  }

  public Sales deleteById(Long id) {
    Sales sales = findSaleById(id);

    salesRepository.deleteById(id);

    return sales;
  }

  public Sales createSale(String token, SalesCreateDto salesCreateDto) {
    String username = tokenService.validateToken(token);
    // Encontre o usuário associado ao token
    Person person = personRepository.findByusername(username).orElseThrow(
        PersonNotFoundException::new);

    Sales sales = salesCreateDto.toEntity();
    sales.setPerson(person);

    List<SaleItem> saleItems = new ArrayList<>();
    for (SaleItemCreateDto itemDto : salesCreateDto.saleItems()) {
      Product product = productRepository.findById(itemDto.productId())
          .orElseThrow(ProductNotFoundException::new);
      SaleItem saleNewItem = new SaleItem(itemDto.quantify(), product);
      saleNewItem.setSale(sales);
      saleItems.add(saleNewItem);
    }
    sales.setSaleItems(saleItems);
    salesRepository.save(sales);

    // Retorne a venda com os itens salvos
    return salesRepository.findById(sales.getId())
        .orElseThrow(() -> new SaleNotFoundException("Venda não encontrada!"));
  }


  public Sales updateSale(Long id, Sales sales) {

    Sales salesFromDb = findSaleById(id);

    salesFromDb.setSaleDate(sales.getSaleDate());
    salesFromDb.setPerson(sales.getPerson());

    return salesRepository.save(salesFromDb);
  }

  public Sales setSalePerson(Long saleId, Long personId)
      throws SaleNotFoundException, PersonNotFoundException {
    Sales sales = findSaleById(saleId);
    Person person = personService.findPersonById(personId);

    sales.setPerson(person);

    return salesRepository.save(sales);
  }

  public Sales deleteSalePerson(Long saleId) {
    Sales sales = findSaleById(saleId);

    sales.setPerson(null);

    return salesRepository.save(sales);
  }

}
