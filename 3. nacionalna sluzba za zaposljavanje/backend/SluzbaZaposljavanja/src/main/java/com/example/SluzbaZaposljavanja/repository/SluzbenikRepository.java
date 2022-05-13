package com.example.SluzbaZaposljavanja.repository;

import com.example.SluzbaZaposljavanja.model.Sluzbenik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SluzbenikRepository extends JpaRepository<Sluzbenik, Integer> {
}
