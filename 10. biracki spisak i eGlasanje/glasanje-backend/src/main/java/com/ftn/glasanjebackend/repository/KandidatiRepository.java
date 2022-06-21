package com.ftn.glasanjebackend.repository;

import com.ftn.glasanjebackend.model.Kandidat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KandidatiRepository extends JpaRepository<Kandidat, Long> {
    List<Kandidat> findAll();
}
