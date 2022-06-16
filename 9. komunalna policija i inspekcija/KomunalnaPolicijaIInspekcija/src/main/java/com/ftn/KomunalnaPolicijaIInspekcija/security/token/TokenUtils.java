package com.ftn.KomunalnaPolicijaIInspekcija.security.token;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenUtils {

    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }
}
