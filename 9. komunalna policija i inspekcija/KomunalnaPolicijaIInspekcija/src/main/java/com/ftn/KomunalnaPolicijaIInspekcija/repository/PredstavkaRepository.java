package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Predstavka;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredstavkaRepository extends JpaRepository<Predstavka, Long> {

    Predstavka findPredstavkaById(Long id);
    List<Predstavka> findPredstavkasByPodnosilac(Podnosilac podnosilac);
}
