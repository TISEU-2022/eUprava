package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UtilService {

    @Autowired
    private UserService userService;


    public void createUsers(){
        User user = new User();
        user.setUsername("milos");
        user.setRole("SLUZBENIK");
        user.setAddress("Novi Sad");
        user.setCitizenship("Srbija");
        user.setEmail("milos@gmail.com");
        user.setIdentificationNumber("1409988172850");
        user.setName("Milos");
        user.setLastname("Simic");
        user.setCityOfBirth("Novi Sad");
        user.setCountryOfBirth("Srbija");
        user.setDateOfBirth(LocalDate.of(1988,9,14));
        user.setGender("Musko");

        User user2 = new User();
        user2.setUsername("djole");
        user2.setRole("GRADJANIN");
        user2.setAddress("Novi Sad");
        user2.setCitizenship("Srbija");
        user2.setEmail("djole@gmail.com");
        user2.setIdentificationNumber("1409999170850");
        user2.setName("Djordje");
        user2.setLastname("Vuckovic");
        user2.setCityOfBirth("Novi Sad");
        user2.setCountryOfBirth("Srbija");
        user2.setDateOfBirth(LocalDate.of(1999,9,14));
        user2.setGender("Musko");
        userService.save(user);
        userService.save(user2);



    }
}