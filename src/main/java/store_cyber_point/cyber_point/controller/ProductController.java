package store_cyber_point.cyber_point.controller;

import java.util.Optional;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import store_cyber_point.cyber_point.Entity.Product;
import store_cyber_point.cyber_point.dto.ProductCreateDto;
import store_cyber_point.cyber_point.dto.ProductDto;
import store_cyber_point.cyber_point.exception.ProductNotFoundException;
import store_cyber_point.cyber_point.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductCreateDto productCreateDto) {
    Optional<Product> existingProduct = productService.findProductByName(productCreateDto.name());

    if (existingProduct.isPresent()) {
    return ResponseEntity.ok(ProductDto.fromEntity(existingProduct.get()));
    }

ProductDto createdProduct = ProductDto.fromEntity(productService.create(productCreateDto.toEntity()));
    return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

  }

  @GetMapping
  public List<ProductDto> getAllProducts() {
    List<Product> products = productService.findAll();
    return products.stream()
        .map(ProductDto::fromEntity)
        .toList();
  }

  @GetMapping("/{id}")
  public ProductDto findId(@PathVariable Long id) {
    return ProductDto.fromEntity(productService.findById(id));
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProductDto updateProduct(@PathVariable Long id,
      @RequestBody ProductCreateDto productCreateDto)
      throws ProductNotFoundException {
    return ProductDto.fromEntity(productService.updateProduct(id, productCreateDto.toEntity()));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws ProductNotFoundException { //dto nao é util aqui, so em get, post e put
    productService.deleteProduct(id);
    return ResponseEntity.ok("Produto deletado com sucesso!");
  }

}
