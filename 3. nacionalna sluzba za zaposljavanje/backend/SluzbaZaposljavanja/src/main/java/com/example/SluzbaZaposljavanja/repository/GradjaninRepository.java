package com.example.SluzbaZaposljavanja.repository;

import com.example.SluzbaZaposljavanja.model.Gradjanin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradjaninRepository extends JpaRepository<Gradjanin, Integer> {

    Gradjanin findByKorisnickoIme(String korisnickoIme);
}
