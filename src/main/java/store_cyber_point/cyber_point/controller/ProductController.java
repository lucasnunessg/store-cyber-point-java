package store_cyber_point.cyber_point.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import store_cyber_point.cyber_point.Entity.Product;
import store_cyber_point.cyber_point.dto.ProductCreateDto;
import store_cyber_point.cyber_point.dto.ProductDto;
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
  public ProductDto createProduct(@RequestBody ProductCreateDto productCreateDto) {
    return ProductDto.fromEntity(productService.create(productCreateDto.toEntity()));
  }

  @GetMapping
  public List<ProductDto> getAllProducts() {
    List<Product> products = productService.findAll();
    return products.stream()
        .map(ProductDto::fromEntity)
        .toList();
  }

}
