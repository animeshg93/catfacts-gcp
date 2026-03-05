package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.service.TokenService;
import kong.unirest.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/token")
public class TokenController {

  private final TokenService tokenService;

  public TokenController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @GetMapping
  public  ResponseEntity<Map<String, String>> getAccessToken() {
    String token = tokenService.fetchAccessToken();
    Map<String, String> response = new HashMap<>();
    response.put("accessToken", token);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
