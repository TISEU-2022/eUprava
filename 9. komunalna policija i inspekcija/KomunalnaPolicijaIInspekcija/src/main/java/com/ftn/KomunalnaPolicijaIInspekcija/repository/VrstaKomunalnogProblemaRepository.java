package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaKomunalnogProblema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VrstaKomunalnogProblemaRepository extends JpaRepository<VrstaKomunalnogProblema, Long> {

    VrstaKomunalnogProblema findVrstaKomunalnogProblemaById(Long id);
}
