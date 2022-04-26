package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.SluzbenikDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.SluzbenikRepository;
import com.ftn.KomunalnaPolicijaIInspekcija.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class SluzbenikServiceImpl implements SluzbenikService {

    private final SluzbenikRepository sluzbenikRepository;

    public SluzbenikServiceImpl(SluzbenikRepository sluzbenikRepository){
        this.sluzbenikRepository = sluzbenikRepository;
    }


    @Override
    public SluzbenikDTO getOne(UUID id) {
        return null;
    }

    @Override
    public UUID createSluzbenik(SluzbenikDTO sluzbenikDTO) {
        return null;
    }

    @Override
    public void updateSluzbenik(SluzbenikDTO sluzbenikDTO) {

    }

    @Override
    public void deleteSluzbenik(UUID id) {

    }
}
