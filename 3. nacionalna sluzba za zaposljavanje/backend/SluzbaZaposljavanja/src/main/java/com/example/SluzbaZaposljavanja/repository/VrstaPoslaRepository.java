package com.example.SluzbaZaposljavanja.repository;

import com.example.SluzbaZaposljavanja.model.VrstaPosla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VrstaPoslaRepository extends JpaRepository<VrstaPosla, Integer> {
}
