package com.cyberpoint.controller;

import com.cyberpoint.exception.PersonNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.cyberpoint.entity.Product;
import com.cyberpoint.dto.ProductCreateDto;
import com.cyberpoint.dto.ProductDto;
import com.cyberpoint.exception.ProductNotFoundException;
import com.cyberpoint.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductCreateDto productCreateDto) {
    Optional<Product> existingProduct = productService.findProductByName(productCreateDto.name());

    // if (existingProduct.isPresent()) {
    // return ResponseEntity.ok(ProductDto.fromEntity(existingProduct.get()));
    // }

    ProductDto createdProduct = ProductDto.fromEntity(
        productService.create(productCreateDto.toEntity()));
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
  @PreAuthorize("hasRole('ADMIN')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<String> deleteProduct(@PathVariable Long id)
      throws ProductNotFoundException {
    productService.deleteProduct(id);
    return ResponseEntity.ok("Produto deletado com sucesso!");
  }

  // @PutMapping("/{productId}/persons/{personId}")
  // public ProductDto setProductPerson(@PathVariable Long productId, @PathVariable Long personId)
  //     throws ProductNotFoundException, PersonNotFoundException {
  //  return ProductDto.fromEntity(productService.setProductPerson(productId, personId)
  //  );
  // }

  //  @DeleteMapping("/{productId}/persons)")
  // public ProductDto deleteProductPerson(@PathVariable Long productId)
//      throws ProductNotFoundException {
  //   return ProductDto.fromEntity(productService.deleteProductPerson(productId));
//  }

  /*
  Fazendo o get do relacionamento.
   */

  // @GetMapping("/{productId}/persons")
  // public ProductDto getAllProductsPersons(@PathVariable Long productId) {
  //  Product product = productService.findByIdWithPerson(productId);
  //  return ProductDto.fromEntity(product);
  // }

}
