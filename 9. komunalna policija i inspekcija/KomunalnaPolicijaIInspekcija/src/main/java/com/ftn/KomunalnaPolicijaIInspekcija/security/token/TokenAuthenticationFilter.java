package com.ftn.KomunalnaPolicijaIInspekcija.security.token;

import com.ftn.KomunalnaPolicijaIInspekcija.security.response.ClaimsData;
import com.ftn.KomunalnaPolicijaIInspekcija.security.response.UserDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenUtils tokenUtils;

    public TokenAuthenticationFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = tokenUtils.getToken(request);

        if(token != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            RestTemplate template = new RestTemplate();
            ResponseEntity<UserDetailsResponse> authResponse = template.getForEntity("http://auth-app:3101/auth/verify_token/" + token, UserDetailsResponse.class);

            if(authResponse.getStatusCode().is2xxSuccessful() && authResponse.getBody() != null) {
                ClaimsData claimsData = authResponse.getBody().getClaims();

                if(claimsData != null) {
                    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    for(String role: claimsData.getRoles()) {
                        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                    }

                    UserDetails userDetails = new User(claimsData.getUsername(), claimsData.getIdentityNumber(), grantedAuthorities);
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
