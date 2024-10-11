package com.cyberpoint.service;

import com.cyberpoint.exception.ProductDuplicateException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.cyberpoint.entity.Product;
import com.cyberpoint.exception.ProductNotFoundException;
import com.cyberpoint.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
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


}
