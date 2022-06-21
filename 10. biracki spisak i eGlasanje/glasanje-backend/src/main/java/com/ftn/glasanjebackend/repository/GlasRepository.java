package com.ftn.glasanjebackend.repository;

import com.ftn.glasanjebackend.model.Glas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GlasRepository extends JpaRepository<Glas, Long> {
//    Glas findGlasByKorisnikIdAndIzboriId(Long korisnikId, Long izboriId);
    List<Glas> findGlasByKandidatIdAndIzboriId(Long kandidatId, Long izboriId);
}
