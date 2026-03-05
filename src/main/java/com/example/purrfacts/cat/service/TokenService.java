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
          .body(
              "{\"client_id\":\"70jBOJCZ9M0wqa1DjwuSbeSIgFMKOSia\",\"client_secret\":\"fh8Vo2UjA0WMLn-n2TLRtUDe-45b12I2QMHJMn-GOlobRb19SXRc_eb22eaE4h_f\",\"audience\":\"https://cat-facts.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
          .asString()
          .getBody();

    } catch (Exception e) {
      throw new RuntimeException("Failed to fetch access token: " + e.getMessage(), e);
    }
  }
}
