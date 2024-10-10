package store_cyber_point.cyber_point.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.stereotype.Service;
import store_cyber_point.cyber_point.Entity.Product;
import store_cyber_point.cyber_point.dto.ProductDto;
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

  public Product updateProduct(Long id, Product product) {
    Product productDb = findById(id);

    productDb.setId(product.getId());
    productDb.setName(product.getName());
    productDb.setPrice(product.getPrice());
    productDb.setDescription(product.getDescription());
    productDb.setImage(product.getImage());

    return productRepository.save(productDb);
  }

  public Product create(Product product) {
    return productRepository.save(product);
  }

  public Product deleteProduct(Long id) {
    Product product = findById(id);

    productRepository.deleteById(id);

    return product;
  }

  public Product getProductByname(String name) {
    Optional<Product> product = productRepository.findByname(name);
    if(product.isEmpty()){
      throw  new ProductNotFoundException();
    }

    return product.get();
  }


}
