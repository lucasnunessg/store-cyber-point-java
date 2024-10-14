package com.cyberpoint;

import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import org.springframework.stereotype.Component;
import com.cyberpoint.entity.Product;
import com.cyberpoint.repository.ProductRepository;


//essa classe é independente a import.sql, aqui posso fazer mais inserções ao subir aplicação independente da import.sql
@Component
public class DatabaseSeeder implements CommandLineRunner {

  private final ProductRepository productRepository;

  public DatabaseSeeder(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    seedProducts();
  }

  private void seedProducts() {
    List<Product> products = new ArrayList<>();
    products.add(new Product("Cadeira gamer", 1500d, "Cadeira ergometrica",
        "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRA8skCQjfadR_IwFKCbHBIQGOglbKGOESbkBGxE7w64Sy1wa7SWHBRSM4cd1bdWOYQu6cQLOuFCIyPjwuVU_Zb0AExrSxPubrYDlDTCsyw9xE-WwvIHAN9&usqp=CAE"));

    productRepository.saveAll(products);
  }



//  private void seedPerson()


}
