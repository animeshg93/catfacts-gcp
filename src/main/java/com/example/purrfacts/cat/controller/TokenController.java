package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.model.AuthToken;
import com.example.purrfacts.cat.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/token")
public class TokenController {

  private final TokenService tokenService;

  public TokenController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @GetMapping
  public ResponseEntity<AuthToken> getAccessToken() {
    AuthToken token = tokenService.fetchAccessToken();
    return ResponseEntity.status(HttpStatus.OK).body(token);
  }
}
