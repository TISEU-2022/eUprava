package com.ftn.glasanjebackend.repository;

import com.ftn.glasanjebackend.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisniciRepository extends JpaRepository<Korisnik, Long> {

    Korisnik findKorisnikByJmbgAndLozinka(String jmbg, String lozinka);

}
