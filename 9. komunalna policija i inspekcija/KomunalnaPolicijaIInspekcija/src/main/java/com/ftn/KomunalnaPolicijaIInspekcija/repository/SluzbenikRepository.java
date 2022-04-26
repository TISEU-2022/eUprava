package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SluzbenikRepository extends JpaRepository<Sluzbenik, UUID> {
}
