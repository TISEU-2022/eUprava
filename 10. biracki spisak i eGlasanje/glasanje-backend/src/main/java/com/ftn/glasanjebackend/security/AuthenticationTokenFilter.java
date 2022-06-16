package com.ftn.glasanjebackend.security;

import com.ftn.glasanjebackend.security.model.ClaimsData;
import com.ftn.glasanjebackend.security.model.TokenBasedAuthentication;
import com.ftn.glasanjebackend.security.model.UserDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenUtils tokenUtils;


//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = httpServletRequest.getHeader("Authorization");
//        if(token != null){
//            if(token.startsWith("Bearer ")){
//                token = token.substring(7);
//            }
//        }
//        if(token != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            String username = tokenUtils.getUsernameFromToken(token);
//
//            RestTemplate template = new RestTemplate();
//            ResponseEntity<UserDetailsResponse> authResponse = template.getForEntity("http://auth-app:3101/auth/verify_token/" + token, UserDetailsResponse.class);
//
//            if(authResponse.getStatusCode().is2xxSuccessful() && authResponse.getBody() != null){
//                ClaimsData claimsData = authResponse.getBody().getClaims();
//
//                if(claimsData != null) {
//                    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//                    for(String role: claimsData.getRoles()) {
//                        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
//                    }
//
//                    UserDetails userDetails = new User(claimsData.getUsername(), claimsData.getIdentityNumber(), grantedAuthorities);
//                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
//                    authentication.setToken(token);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//
//            chain.doFilter(request, response);
//        }
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        if(token != null){
            if(token.startsWith("Bearer ")){
                token = token.substring(7);
            }
        }
        if(token != null && SecurityContextHolder.getContext().getAuthentication() == null){
            String username = tokenUtils.getUsernameFromToken(token);

            RestTemplate template = new RestTemplate();
            ResponseEntity<UserDetailsResponse> authResponse = template.getForEntity("http://auth-app:3101/auth/verify_token/" + token, UserDetailsResponse.class);

            if(authResponse.getStatusCode().is2xxSuccessful() && authResponse.getBody() != null){
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
            filterChain.doFilter(request, response);
        }
    }
}
