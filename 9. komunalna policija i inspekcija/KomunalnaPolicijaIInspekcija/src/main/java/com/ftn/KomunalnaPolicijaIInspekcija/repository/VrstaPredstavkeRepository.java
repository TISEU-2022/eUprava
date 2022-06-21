package com.ftn.KomunalnaPolicijaIInspekcija.repository;

import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaPredstavke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VrstaPredstavkeRepository extends JpaRepository<VrstaPredstavke, Long> {

    VrstaPredstavke findVrstaPredstavkeById(Long id);
}
