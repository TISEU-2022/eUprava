package com.example.SluzbaZaposljavanja.repository;

import com.example.SluzbaZaposljavanja.model.Firma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmaRepository extends JpaRepository<Firma, Integer> {
}
