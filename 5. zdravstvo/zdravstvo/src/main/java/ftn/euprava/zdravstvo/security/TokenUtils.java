package ftn.euprava.zdravstvo.security;


import ftn.euprava.zdravstvo.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

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