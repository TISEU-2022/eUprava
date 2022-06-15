package com.ftn.glasanjebackend.service;


import com.ftn.glasanjebackend.model.Korisnik;
import com.ftn.glasanjebackend.model.dto.KorisnikDTO;

public interface UserService {

    Korisnik findByUsername(String username);
    Korisnik createUser(KorisnikDTO korisnikDTO);
    Korisnik findOne(Integer id);

    Korisnik save(Korisnik korisnik);
}
