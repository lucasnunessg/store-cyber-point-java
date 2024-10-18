package com.cyberpoint.controller;

import com.cyberpoint.dto.AuthDto;
import com.cyberpoint.dto.TokenDto;
import com.cyberpoint.service.TokenService;
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
  private final TokenService tokenService;

  @Autowired
  public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public TokenDto login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = //esse eu uso para pegar o que eu passei no Dto. Encapsula as credenciais.
        new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password()); //aqui eu posso passar email , basta acrescentar la no dto e repository.

  Authentication auth = authenticationManager.authenticate(usernamePassword);
  String token = tokenService.generateToken(auth.getName());

  return new TokenDto(token); //criar exceção p login errado

  }

}
