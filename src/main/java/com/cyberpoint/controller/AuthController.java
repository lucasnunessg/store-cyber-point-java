package com.cyberpoint.controller;

import com.cyberpoint.dto.AuthDto;
import com.cyberpoint.dto.TokenDto;
import com.cyberpoint.exception.LoginErrorException;
import com.cyberpoint.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
  public ResponseEntity<TokenDto> login(@RequestBody AuthDto authDto) {
    try {
      UsernamePasswordAuthenticationToken usernamePassword =
          new UsernamePasswordAuthenticationToken(authDto.username(), authDto.password());

      Authentication auth = authenticationManager.authenticate(usernamePassword);

      String role = auth.getAuthorities().stream()
          .findFirst() //encontrar a primeira role q tiver
          .map(Object::toString) // tansformar numa string
          .orElseThrow(() -> new RuntimeException("Role não encontrada!"));

      String token = tokenService.generateToken(auth.getName(), role);

      return ResponseEntity.ok(new TokenDto(token));

    } catch (BadCredentialsException e) {
      throw new LoginErrorException("Usuário ou senha incorretos.");
    }
  }

}
