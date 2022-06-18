package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.SluzbenikDTO;

public interface SluzbenikService {

    SluzbenikDTO getOne(Long id);
    Long createSluzbenik(SluzbenikDTO sluzbenikDTO);
    void updateSluzbenik(Long uuid, SluzbenikDTO sluzbenikDTO);
    boolean deleteSluzbenik(Long id);
    SluzbenikDTO getByJmbg(String jmbg);
}
