package com.example.SluzbaZaposljavanja.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.SluzbaZaposljavanja.service.impl.AuthTokenService;

@Component
public class JwtUtil {
	
    @Autowired
    private AuthTokenService authTokenService;

    public String getUsernameFromToken(String token) {
        String username = authTokenService.getUsernameFromToken(token);
        return username;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername())
                && authTokenService.isTokenValid(token);
    }

}
