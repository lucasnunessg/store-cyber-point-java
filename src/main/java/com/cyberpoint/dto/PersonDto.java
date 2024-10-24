package com.cyberpoint.dto;

import com.cyberpoint.entity.Person;
import com.cyberpoint.security.Role;

public record PersonDto(Long id, String fullname, String username, String email, String address,
                        Role role) {

  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getFullname(),
        person.getUsername(),
        person.getEmail(),
        person.getAddress(),
        person.getRole()

    );
  }
}
