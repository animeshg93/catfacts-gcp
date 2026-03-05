package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.service.TokenService;
import kong.unirest.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class TokenController {

  private final TokenService tokenService;

  public TokenController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @GetMapping
  public ResponseEntity<JSONObject> getAccessToken() {
    try {
      JSONObject tokenResponse = tokenService.fetchAccessToken();
      return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
    } catch (RuntimeException e) {
      JSONObject errorResponse = new JSONObject();
      errorResponse.put("error", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
  }
}
