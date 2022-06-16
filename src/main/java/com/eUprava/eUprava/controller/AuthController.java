package com.eUprava.eUprava.controller;

import com.eUprava.eUprava.model.dto.ErrorDTO;
import com.eUprava.eUprava.model.dto.KorisnikDTO;
import com.eUprava.eUprava.model.dto.LoginDTO;
import com.eUprava.eUprava.model.entity.User;
import com.eUprava.eUprava.security.TokenUtils;
import com.eUprava.eUprava.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginDTO user) {
        logger.info("Login attempt by user {}", user.getUsername());
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getIdentificationNumber());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());
            User userDb = userService.findByUsername(user.getUsername());
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            logger.debug("Nevalidni podaci");
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDTO("Nevalidni podaci"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/myProfile")
    public ResponseEntity<?> myProfile(@RequestHeader("Authorization") String token) {
        KorisnikDTO korisnik = null;
        try {
            korisnik = userService.myProfile(token);
            return new ResponseEntity<>(korisnik, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
