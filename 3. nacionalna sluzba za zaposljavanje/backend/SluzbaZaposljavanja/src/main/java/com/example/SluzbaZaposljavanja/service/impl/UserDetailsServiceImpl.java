package com.example.SluzbaZaposljavanja.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SluzbaZaposljavanja.model.Gradjanin;
import com.example.SluzbaZaposljavanja.service.GradjaninService;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService{
	
    @Autowired
    private GradjaninService gradjaninService;

    @Transactional
    public UserDetails loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
        Gradjanin gradjanin =  this.gradjaninService.findByKorisnickoIme(korisnickoIme);
        if (gradjanin == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", korisnickoIme));
        }
        else {
            List<GrantedAuthority> grantedAuthorities = new ArrayList();
            String role = "ROLE_" + gradjanin.getRole();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
            return new org.springframework.security.core.userdetails.User(gradjanin.getKorisnickoIme().trim(),
                    "", grantedAuthorities);
        }
    }

}
