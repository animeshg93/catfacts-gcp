package com.example.purrfacts.cat.service;

import com.example.purrfacts.cat.exception.HttpResponseException;
import com.example.purrfacts.cat.model.AuthToken;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private static final Logger log = LoggerFactory.getLogger(TokenService.class);

  @Value("${auth.token.api}")
  private String tokenApiUrl;

  @Value("${auth.client-id}")
  private String clientId;

  @Value("${auth.client-secret}")
  private String clientSecret;

  public TokenService() {}

  public AuthToken fetchAccessToken() {
    HttpResponse<AuthToken> response =
        Unirest.post(tokenApiUrl)
            .header("content-type", "application/json")
            .basicAuth(clientId, clientSecret)
            .body(
                "{\"audience\":\"https://cat-facts.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
            .asObject(AuthToken.class);

    if (!response.isSuccess()) {
      log.error("Failed to fetch access token. Status: {}", response.getStatus());
      throw new HttpResponseException(
          response.getStatus(), response.getStatusText(), response.getBody().toString());
    }

    return response.getBody();
  }
}
