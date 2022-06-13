package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.model.entity.User;
import com.eUprava.eUprava.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

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
}
