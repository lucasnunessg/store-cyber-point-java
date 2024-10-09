package store_cyber_point.cyber_point;

import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import java.util.List;
import org.springframework.stereotype.Component;
import store_cyber_point.cyber_point.Entity.Product;
import store_cyber_point.cyber_point.repository.ProductRepository;

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

  }
}
