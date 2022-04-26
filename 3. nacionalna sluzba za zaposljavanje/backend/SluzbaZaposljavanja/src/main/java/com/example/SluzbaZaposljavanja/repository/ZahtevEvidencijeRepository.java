package com.example.SluzbaZaposljavanja.repository;

import com.example.SluzbaZaposljavanja.model.ZahtevEvidencije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZahtevEvidencijeRepository extends JpaRepository<ZahtevEvidencije, Integer> {
}
