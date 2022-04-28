package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SluzbenikRepository extends JpaRepository<Sluzbenik, Long> {

    Sluzbenik findSluzbenikById(Long uuid);
}
