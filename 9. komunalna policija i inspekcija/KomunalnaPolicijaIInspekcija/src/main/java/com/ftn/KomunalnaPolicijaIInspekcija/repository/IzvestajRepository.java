package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Izvestaj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IzvestajRepository extends JpaRepository<Izvestaj, Long> {
}
