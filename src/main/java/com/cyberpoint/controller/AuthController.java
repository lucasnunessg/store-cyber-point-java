package com.cyberpoint.controller;

import com.cyberpoint.dto.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager; //ele quem verifica as credenciais e autentica as mesmas e faz a linha 29 dar certo.

  @Autowired
  public AuthController(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public String login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = //esse eu uso para pegar o que eu passei no Dto. Encapsula as credenciais.
        new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password()); //aqui eu posso passar email , basta acrescentar la no dto e repository.

  Authentication auth = authenticationManager.authenticate(usernamePassword);

  return "Login efetuado com sucesso: %s".formatted(auth.getName()); //criar exceção p login errado
  }

}
