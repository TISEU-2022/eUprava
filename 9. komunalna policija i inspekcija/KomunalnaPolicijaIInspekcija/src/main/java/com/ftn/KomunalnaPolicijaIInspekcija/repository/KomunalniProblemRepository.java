package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface KomunalniProblemRepository extends JpaRepository<KomunalniProblem, Long> {

    List<KomunalniProblem> findKomunalniProblemsByPodnosilac(Podnosilac podnosilac);
    KomunalniProblem findKomunalniProblemById(Long id);
    void deleteKomunalniProblemById(Long id);
}
