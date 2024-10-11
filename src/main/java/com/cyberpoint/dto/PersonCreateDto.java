package com.cyberpoint.dto;

import com.cyberpoint.entity.Person;

public record PersonCreateDto(String fullname, String username, String email, String password, String address) {
  public Person toEntity() {
    return new Person(fullname, username, email, password, address);
  }
}
