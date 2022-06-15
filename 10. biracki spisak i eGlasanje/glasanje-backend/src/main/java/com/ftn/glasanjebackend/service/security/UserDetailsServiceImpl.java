package com.ftn.glasanjebackend.service.security;

import com.ftn.glasanjebackend.model.Korisnik;
import com.ftn.glasanjebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//Primary je neophodno da bi naglasili Spring Boot-u da zelimo bas ovaj UserDetailService kada budemo koristili
//Autowired pri konfiguraciji security-a
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Korisnik user = userService.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("There is no user with jmbg " + s);
        }else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            String role = "ROLE_" + user.getSluzbenik().toString();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));

            return new org.springframework.security.core.userdetails.User(
                    user.getJmbg().trim(),
                    user.getLozinka().trim(),
                    grantedAuthorities);
        }

    }
}
