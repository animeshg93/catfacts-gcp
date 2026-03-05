package com.example.purrfacts.cat.service;

import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("${auth.token.api}")
    private String tokenApiUrl;
    @Value("${auth.client-id}")
    private String clientId;
    @Value("${auth.client-secret}")
    private String clientSecret;

    public TokenService() {
    }

    public String fetchAccessToken() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Prepare request body with credentials
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("grant_type", "client_credentials");
            requestBody.put("audience", "https://cat-facts.com/api/v2/");
            requestBody.put("client_id", clientId);
            requestBody.put("client_secret", clientSecret);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            return Unirest.post(tokenApiUrl)
                    .header("content-type", "x-www-form-urlencoded")
                    .body(requestBody)
                    .asString().getBody();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch access token: " + e.getMessage(), e);
        }
    }
}
