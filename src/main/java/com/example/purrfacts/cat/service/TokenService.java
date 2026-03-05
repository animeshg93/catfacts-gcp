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
      return Unirest.post("https://dev-xjtz6ps1llg7ich0.us.auth0.com/oauth/token")
          .header("content-type", "application/json")
          .basicAuth(clientId, clientSecret)
          .body(
              "{\"audience\":\"https://cat-facts.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
          .asString()
          .getBody();

    } catch (Exception e) {
      throw new RuntimeException("Failed to fetch access token: " + e.getMessage(), e);
    }
  }
}
