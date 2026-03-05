package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.service.TokenService;
import java.util.HashMap;
import java.util.Map;

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
    JSONObject token = tokenService.fetchAccessToken();
    return ResponseEntity.status(HttpStatus.OK).body(token);
  }
}
