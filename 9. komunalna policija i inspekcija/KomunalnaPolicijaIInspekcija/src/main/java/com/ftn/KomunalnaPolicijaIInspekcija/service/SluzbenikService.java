package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.SluzbenikDTO;

import java.util.UUID;

public interface SluzbenikService {

    SluzbenikDTO getOne(UUID id);
    UUID createSluzbenik(SluzbenikDTO sluzbenikDTO);
    void updateSluzbenik(UUID uuid, SluzbenikDTO sluzbenikDTO);
    boolean deleteSluzbenik(UUID id);

}
