package com.cyberpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// @EnableJpaRepositories
public class CyberPointApplication {

  // @Bean
//  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() { return new PersistenceExceptionTranslationPostProcessor(); }

  public static void main(String[] args) {
    SpringApplication.run(CyberPointApplication.class, args);
  }

}
