package com.eUprava.eUprava.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthTokenService {

    final private UserService userService;

    //Local
    private static final String AUTH_APP_URI = "http://localhost:5101/auth/verify_token/";


    public AuthTokenService(UserService userService) {
        this.userService = userService;
    }

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