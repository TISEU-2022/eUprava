package com.example.SluzbaZaposljavanja.repository;

import com.example.SluzbaZaposljavanja.model.Gradjanin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradjaninRepository extends JpaRepository<Gradjanin, Integer> {
<<<<<<< HEAD
<<<<<<< HEAD
=======

    Gradjanin findByKorisnickoIme(String korisnickoIme);

>>>>>>> zaposljavanje-init
=======

    Gradjanin findByKorisnickoIme(String korisnickoIme);
>>>>>>> 9aa1fa015c0a422bba3229b3ff6d5876c08f41ad
}
