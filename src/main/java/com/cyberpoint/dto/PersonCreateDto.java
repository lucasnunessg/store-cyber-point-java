package com.cyberpoint.dto;

import com.cyberpoint.entity.Person;
import com.cyberpoint.security.Role;

public record PersonCreateDto(String fullname, String username, String email, String password,
                              String address, Role role) {

  public Person toEntity() {
    return new Person(fullname, username, email, password, address, role);
  }
}
