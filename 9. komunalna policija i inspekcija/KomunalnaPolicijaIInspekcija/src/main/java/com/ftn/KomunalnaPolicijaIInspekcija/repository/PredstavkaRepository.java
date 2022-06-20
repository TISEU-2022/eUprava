package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Predstavka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredstavkaRepository extends JpaRepository<Predstavka, Long> {

    Predstavka findPredstavkaById(Long id);
}
