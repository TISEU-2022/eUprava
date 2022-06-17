package com.ftn.glasanjebackend.kontroleri;


import com.ftn.glasanjebackend.model.Korisnik;
import com.ftn.glasanjebackend.model.dto.KorisnikDTO;
import com.ftn.glasanjebackend.model.dto.KorisnikPrijavaDTO;
import com.ftn.glasanjebackend.security.TokenUtils;
import com.ftn.glasanjebackend.service.KorisniciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/korisnici")
@CrossOrigin
public class KorisniciKontroler {

    @Autowired
    private KorisniciService korisniciService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;



    @PostMapping(value = "/prijava")
    public ResponseEntity<String> prijava(@RequestBody KorisnikPrijavaDTO korisnikPrijavaDTO){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(korisnikPrijavaDTO.getJmbg(), korisnikPrijavaDTO.getLozinka());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(korisnikPrijavaDTO.getJmbg());
            String token = tokenUtils.generateToken(userDetails);
            System.out.println(token);
            return ResponseEntity.ok(token);
        } catch (UsernameNotFoundException e){
            return ResponseEntity.noContent().build();
        }
    }
}
