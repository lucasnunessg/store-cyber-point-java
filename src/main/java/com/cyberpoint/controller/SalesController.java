package com.cyberpoint.controller;

import com.cyberpoint.dto.PersonCreateDto;
import com.cyberpoint.dto.PersonDto;
import com.cyberpoint.dto.SalesCreateDto;
import com.cyberpoint.dto.SalesDto;
import com.cyberpoint.entity.Person;
import com.cyberpoint.entity.Sales;
import com.cyberpoint.exception.SaleNotFoundException;
import com.cyberpoint.service.PersonService;
import com.cyberpoint.service.SaleService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

  private final SaleService saleService;
  private final PersonService personService;

  @Autowired
  public SalesController(SaleService saleService, PersonService personService) {
    this.saleService = saleService;
    this.personService = personService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SalesDto create(@RequestBody SalesCreateDto salesCreateDto) throws SaleNotFoundException {
    Sales sales = salesCreateDto.toEntity(); // Cria a entidade Sales
    Sales createdSale = saleService.createSale(sales); // Salva a venda
    return SalesDto.fromEntity(createdSale); // Retorna a venda criada como DTO
  }

  @GetMapping
  public List<SalesDto> getAllSales() {
    List<Sales> sales = saleService.findAll();
    return sales.stream()
        .map(SalesDto::fromEntity)
        .toList();
  }

  @GetMapping("/{id}")
  public SalesDto getSaleById(@PathVariable Long id) {
    return SalesDto.fromEntity(saleService.findSaleById(id));
  }

  @PutMapping("/{id}")
  public SalesDto updateSales(@PathVariable Long id, @RequestBody SalesCreateDto salesCreateDto) {
    return SalesDto.fromEntity(saleService.updateSale(id, salesCreateDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSale(@PathVariable Long id) {
    saleService.deleteById(id);
    return ResponseEntity.ok("Venda deletada com sucesso!");
  }

  @PostMapping("/{saleId}/persons")
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createSalePerson(@PathVariable Long saleId, @RequestBody PersonCreateDto personCreateDto) {
   Sales sales = saleService.findSaleById(saleId);
   if (sales == null) {
     throw new SaleNotFoundException("Venda não encontrada!");
   }
   Person newPerson = personCreateDto.toEntity();
   newPerson = personService.create(newPerson);
   sales.setPerson(newPerson);
   saleService.updateSale(sales.getId(), sales);

   return PersonDto.fromEntity(newPerson);
  }

  @DeleteMapping("/{saleId}/persons")
  public SalesDto deleteSalesPerson(@PathVariable Long saleId) throws SaleNotFoundException {
    return SalesDto.fromEntity(saleService.deleteSalePerson(saleId));
  }
}
