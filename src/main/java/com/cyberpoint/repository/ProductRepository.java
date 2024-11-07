package com.cyberpoint.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cyberpoint.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> findByname(String name);

}
