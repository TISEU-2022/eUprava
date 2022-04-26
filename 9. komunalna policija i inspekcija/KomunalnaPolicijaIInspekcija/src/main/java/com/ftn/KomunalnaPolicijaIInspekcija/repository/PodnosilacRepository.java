package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PodnosilacRepository extends JpaRepository<Podnosilac, UUID> {

    Podnosilac findPodnosilacById(UUID id);
    void deletePodnosilacById(UUID id);

}
