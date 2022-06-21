package com.example.SluzbaZaposljavanja.service.impl;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class AuthTokenService {
	
	private static final String AUTH_APP_URI = System.getenv("AUTH_SERVER_API")+ "/auth/verify_token/";	

    public Boolean isTokenValid(String token){
        final RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForEntity(AUTH_APP_URI + token, JsonNode.class);
            return true;
        }catch (HttpClientErrorException.Unauthorized e){
            return false;
        }

    }

    public String getUsernameFromToken(String token){
        final RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<JsonNode> response
                    = restTemplate.getForEntity(AUTH_APP_URI + token, JsonNode.class);
            return response.getBody().findValue("claims").findValue("username").toString().replaceAll("\"", "");
        }catch (HttpClientErrorException.Unauthorized e){
            return null;
        }

    }
}
