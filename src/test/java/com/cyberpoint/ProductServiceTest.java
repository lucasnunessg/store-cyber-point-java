package com.cyberpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cyberpoint.entity.Product;
import com.cyberpoint.exception.ProductNotFoundException;
import com.cyberpoint.repository.ProductRepository;
import com.cyberpoint.service.ProductService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ProductServiceTest {

  @Autowired
  ProductService productService;

  @MockBean
  ProductRepository productRepository;

  @Test
  public void testProductService() {
    Product product = new Product();
    product.setId(5L);
    product.setName("Computador");
    product.setDescription("Computador bom");
    product.setImage("http");
    product.setPrice(2000d);

    when(productRepository.findById(5L)).thenReturn(Optional.of(product));


    Product returnedProduct = productService.findById(5L);

    assertEquals(returnedProduct.getId(), product.getId());
    assertEquals(returnedProduct.getName(), product.getName());
    assertEquals(returnedProduct.getDescription(), product.getDescription());
    assertEquals(returnedProduct.getImage(), product.getImage());
    assertEquals(returnedProduct.getPrice(), product.getPrice());

  }

  @Test
  public void testProductWithName() {
    Product product = new Product();
    product.setId(5L);
    product.setName("Computador");
    product.setDescription("Computador bom");
    product.setImage("http");
    product.setPrice(2000d);

    when(productRepository.findByname("Computador"))
        .thenReturn(Optional.of(product));

    Optional <Product> returnedNameProduct = productService.findProductByName("Computador");
    verify(productRepository).findByname("Computador");

    assertTrue(returnedNameProduct.isPresent());
    assertEquals(product.getId(), returnedNameProduct.get().getId());
    assertEquals(product.getName(), returnedNameProduct.get().getName());

  }
  @Test
  public void ProductNotFound(){
    when(productRepository.findById(any()))
        .thenReturn(Optional.empty());

    assertThrows(ProductNotFoundException.class, () -> productService.findById(699L));
    Mockito.verify(productRepository).findById(699L);
  }

}

