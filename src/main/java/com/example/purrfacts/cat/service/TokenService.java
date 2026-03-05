package com.example.purrfacts.cat.service;

import com.google.api.client.json.Json;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
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

  public JSONObject fetchAccessToken() {
    try {
      return Unirest.post(tokenApiUrl)
          .header("content-type", "application/json")
          .basicAuth(clientId, clientSecret)
          .body(
              "{\"audience\":\"https://cat-facts.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
          .asJson()
              .getBody().getObject();

    } catch (Exception e) {
      throw new RuntimeException("Failed to fetch access token: " + e.getMessage(), e);
    }
  }
}
