package com.example.SluzbaZaposljavanja.repository;

import com.example.SluzbaZaposljavanja.model.Konkurs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KonkursRepository extends JpaRepository<Konkurs, Integer> {
}
