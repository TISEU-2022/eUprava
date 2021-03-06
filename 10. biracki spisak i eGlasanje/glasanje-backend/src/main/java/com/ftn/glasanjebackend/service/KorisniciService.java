package com.ftn.glasanjebackend.service;

import com.ftn.glasanjebackend.model.Korisnik;
import com.ftn.glasanjebackend.repository.KorisniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisniciService {

    @Autowired
    KorisniciRepository korisniciRepository;

    public Korisnik findKorisnikByJmbgAndLozinka(String jmbg, String lozinka) {

        return korisniciRepository.findKorisnikByJmbgAndLozinka(jmbg, lozinka);
    }

    public Korisnik findById(Long id){
        return korisniciRepository.findById(id).get();
    }

    public Korisnik findByJmbg(String jmbg){

        return korisniciRepository.findKorisnikByJmbg(jmbg);
    }

    public Korisnik save(Korisnik korisnik){
        return  korisniciRepository.save(korisnik);
    }

    public List<Korisnik> findAll(){
        return korisniciRepository.findAll();
    }
}
