package com.cyberpoint.repository;

import com.cyberpoint.entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
  Optional<Person> findByusername(String username);
  boolean existsByUsername(String username);


}
