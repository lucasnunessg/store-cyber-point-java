package store_cyber_point.cyber_point.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store_cyber_point.cyber_point.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
