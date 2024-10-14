package com.cyberpoint.dto;

import com.cyberpoint.entity.Person;

public record PersonDto(Long id, String fullname, String username, String email, String address) {

  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getFullname(),
        person.getUsername(),
        person.getEmail(),
        person.getAddress()

        );
  }
}
