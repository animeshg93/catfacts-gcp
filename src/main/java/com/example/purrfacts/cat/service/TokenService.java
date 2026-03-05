package com.example.purrfacts.cat.service;

import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Value("${auth.token.api}")
  private String tokenApiUrl;

  @Value("${auth.client-id}")
  private String clientId;

  @Value("${auth.client-secret}")
  private String clientSecret;

  public TokenService() {}

  public String fetchAccessToken() {
    try {
      return Unirest.post(tokenApiUrl)
          .basicAuth(clientId, clientSecret)
          .header("content-type", "x-www-form-urlencoded")
          .field("grant_type", "client_credentials")
          .field("audience", "https://cat-facts.com/api/v2/")
          .asString()
          .getBody();

    } catch (Exception e) {
      throw new RuntimeException("Failed to fetch access token: " + e.getMessage(), e);
    }
  }
}
