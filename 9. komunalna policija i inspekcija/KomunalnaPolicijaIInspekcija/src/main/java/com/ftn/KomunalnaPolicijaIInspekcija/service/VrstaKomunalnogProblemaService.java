package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaKomunalnogProblema;

import java.util.List;

public interface VrstaKomunalnogProblemaService {
    VrstaKomunalnogProblema getOne(Long id);
    List<VrstaKomunalnogProblema> findAll();
    Long save(VrstaKomunalnogProblema vrstaKomunalnogProblema);
    void update(Long id, VrstaKomunalnogProblema vrstaKomunalnogProblema);
    boolean delete(Long id);
}
