package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.model.dto.KorisnikDTO;
import com.eUprava.eUprava.model.entity.User;
import com.eUprava.eUprava.repository.UserRepository;
import com.eUprava.eUprava.security.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {



    @Autowired
    TokenUtils tokenUtils;

    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public User getLogged(Authentication authentication){
        return userRepository.findByUsername(authentication.getName());
    }


    public KorisnikDTO myProfile(String token) throws Exception {
        String username = Optional.ofNullable(tokenUtils.getUsernameFromToken(token.split("\\s")[1]))
                .orElseThrow(() -> new Exception("Ulogujte se"));
        User korisnik = Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new Exception("Ulogujte se"));
        KorisnikDTO res = new KorisnikDTO();

        res.setIme(korisnik.getName());
        res.setPrezime(korisnik.getLastname());
        res.setUsername(korisnik.getUsername());
        res.setId(korisnik.getId());
        return res;
    }
}
