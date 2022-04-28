package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodnosilacRepository extends JpaRepository<Podnosilac, Long> {

    Podnosilac findPodnosilacById(Long id);
    void deletePodnosilacById(Long id);
}
