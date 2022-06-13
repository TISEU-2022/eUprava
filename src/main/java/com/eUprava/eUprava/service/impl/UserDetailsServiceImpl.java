package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    public UserDetailsServiceImpl() {
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  this.userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        else {
            List<GrantedAuthority> grantedAuthorities = new ArrayList();
            String role = "ROLE_" + user.getRole();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
            return new org.springframework.security.core.userdetails.User(user.getUsername().trim(),
                    "", grantedAuthorities);
        }
    }
}
