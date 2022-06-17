package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KomunalniProblemRepository extends JpaRepository<KomunalniProblem, Long> {

    KomunalniProblem findKomunalniProblemById(Long id);
    void deleteKomunalniProblemById(Long id);
}
