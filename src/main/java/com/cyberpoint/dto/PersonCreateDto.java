package com.cyberpoint.dto;

import com.cyberpoint.entity.Person;
import com.cyberpoint.security.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PersonCreateDto(
    @NotBlank(message = "Nome completo é necessário!")
    String fullname,
    String username,

    @Email(message = "Digite um email válido!")
    String email,
    @Size(min = 6, message = "Senha deve conter 6 dígitos ou mais")
        @NotBlank(message = "Senha não pode ser vazia!")
    String password,

    String address,
    Role role) {

  public Person toEntity() {
    return new Person(fullname, username, email, password, address, role);
  }
}
