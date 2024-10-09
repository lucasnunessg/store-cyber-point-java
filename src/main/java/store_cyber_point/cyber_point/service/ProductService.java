package store_cyber_point.cyber_point.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;
import store_cyber_point.cyber_point.Entity.Product;
import store_cyber_point.cyber_point.exception.ProductNotFoundException;
import store_cyber_point.cyber_point.repository.ProductRepository;

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

  public Product create(Product product) {
    return productRepository.save(product);
  }
}
