package com.cyberpoint.service;

import com.cyberpoint.entity.Person;
import com.cyberpoint.exception.PersonNotFoundException;
import com.cyberpoint.exception.ProductDuplicateException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.cyberpoint.entity.Product;
import com.cyberpoint.exception.ProductNotFoundException;
import com.cyberpoint.repository.ProductRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;
  private final PersonService personService;

  @Autowired
  public ProductService(ProductRepository productRepository, PersonService personService) {
    this.productRepository = productRepository;
    this.personService = personService;
  }


  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Product findById(Long id) {
    return productRepository.findById((id))
        .orElseThrow(ProductNotFoundException::new);
  }

  public Product updateProduct(Long id, Product product) {
    Product productDb = findById(id);

    productDb.setName(product.getName());
    productDb.setPrice(product.getPrice());
    productDb.setDescription(product.getDescription());
    productDb.setImage(product.getImage());
    productDb.setCategory(product.getCategory());

    return productRepository.save(productDb);
  }

  public Product create(Product product) {
    try {
      return productRepository.save(product);
    } catch (DataIntegrityViolationException e) {
      // Lidar com o erro de duplicidade
      throw new ProductDuplicateException();
    }
  }

  public Product deleteProduct(Long id) {
    Product product = findById(id);

    productRepository.deleteById(id);

    return product;
  }

  public Optional<Product> findProductByName(String name) {

    return productRepository.findByname(name);
  }
  /*
  aqui começa o relacionamento, abaixo.
   */

  // public Product setProductPerson(Long productId, Long personId)
  //    throws ProductNotFoundException, PersonNotFoundException {
  // Product product = findById(productId);
  // Person person = personService.findPersonById(personId);

  // product.setPerson(person);

  // return productRepository.save(product);
  // }

  // public Product deleteProductPerson(Long productId) throws ProductNotFoundException {
  //  Product product = findById(productId);

  //  product.setPerson(null);

  //  return productRepository.save(product);
  // }

  public Product findByIdWithPerson(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(ProductNotFoundException::new);
  }


}
