package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Predstavka;

import java.util.List;

public interface PredstavkaService {

    List<PredstavkaDTO> findAll();
    Predstavka findOne(Long id);
    Predstavka save(Predstavka predstavka);
}
