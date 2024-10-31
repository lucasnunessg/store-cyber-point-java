package com.cyberpoint.service;

import com.cyberpoint.entity.Person;
import com.cyberpoint.entity.Sales;
import com.cyberpoint.exception.PersonNotFoundException;
import com.cyberpoint.exception.SaleNotFoundException;
import com.cyberpoint.repository.SalesRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

  SalesRepository salesRepository;
  PersonService personService;

  @Autowired
  public SaleService(SalesRepository salesRepository, PersonService personService) {
    this.salesRepository = salesRepository;
    this.personService = personService;
  }

  public List<Sales> findAll() {
    return salesRepository.findAll();
  }

  public Sales findSaleById(Long id) {
    Optional<Sales> sale = salesRepository.findById(id);

    if(sale.isEmpty()) {
      throw new SaleNotFoundException("Não encontrado!");
    }

    return sale.get();
  }

  public Sales deleteById(Long id) {
   Sales sales = findSaleById(id);

   salesRepository.deleteById(id);

   return sales;
  }

  public Sales createSale(Sales sales) {

    return salesRepository.save(sales);
  }

  public Sales updateSale(Long id, Sales sales) {
    Sales salesFromDb = findSaleById(id);

    salesFromDb.setSaleDate(sales.getSaleDate());
    salesFromDb.setPerson(sales.getPerson());

    return salesRepository.save(salesFromDb);
  }

  public Sales setSalePerson(Long saleId, Long personId) throws SaleNotFoundException, PersonNotFoundException {
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
