package com.example.SluzbaZaposljavanja.repository;

import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OglasZaPosaoRepository extends JpaRepository<OglasZaPosao, Integer> {
}
