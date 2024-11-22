package com.cyberpoint.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cyberpoint.exception.LoginErrorException;
import com.cyberpoint.security.Role;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private final Algorithm algorithm;

  public TokenService(@Value("api.security.token.secret") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);

  }

  public String generateToken(String username, String role) {
    return JWT.create()
        .withSubject(username)
        .withClaim("role", role)
        .withExpiresAt(generateExpiration())
        .sign(algorithm);
  }

  public Instant generateExpiration() {
    return Instant.now()
        .plus(2, ChronoUnit.HOURS);
  }

  public String validateToken(String token) {
    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();
  }

  public String getRoleFromToken(String token) {
    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getClaim("role").asString();
  }

}
