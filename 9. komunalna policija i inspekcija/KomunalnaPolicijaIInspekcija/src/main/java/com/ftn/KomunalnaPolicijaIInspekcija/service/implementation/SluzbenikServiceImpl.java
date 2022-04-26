package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.SluzbenikDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.SluzbenikMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.SluzbenikRepository;
import com.ftn.KomunalnaPolicijaIInspekcija.service.SluzbenikService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SluzbenikServiceImpl implements SluzbenikService {

    private final SluzbenikRepository sluzbenikRepository;

    public SluzbenikServiceImpl(SluzbenikRepository sluzbenikRepository){
        this.sluzbenikRepository = sluzbenikRepository;
    }

    @Override
    public SluzbenikDTO getOne(UUID id) throws NotFoundException {
        Sluzbenik sluzbenik = sluzbenikRepository.findSluzbenikById(id);
        if(sluzbenik == null){
            throw new NotFoundException("Nema sluzbenika sa id-om: " + id);
        }
        return SluzbenikMapper.mapDTO(sluzbenik);
    }

    @Override
    public UUID createSluzbenik(SluzbenikDTO sluzbenikDTO) {
        UUID id = UUID.randomUUID();
        Sluzbenik sluzbenik = new Sluzbenik(id, sluzbenikDTO.getIme(), sluzbenikDTO.getPrezime(), sluzbenikDTO.getEmail(), sluzbenikDTO.getJmbg());
        sluzbenikRepository.save(sluzbenik);
        return id;
    }

    @Override
    public void updateSluzbenik(UUID uuid, SluzbenikDTO sluzbenikDTO) throws NotFoundException{
        Sluzbenik sluzbenik = sluzbenikRepository.findSluzbenikById(uuid);
        if (sluzbenik == null){
            throw new NotFoundException("Ne postoji sluzbenik sa id-om: " + uuid);
        }
        sluzbenikDTO.setId(uuid);
        sluzbenikRepository.save(SluzbenikMapper.mapModel(sluzbenikDTO));
    }

    @Override
    public boolean deleteSluzbenik(UUID id) {
        Sluzbenik sluzbenik = sluzbenikRepository.findSluzbenikById(id);
        if(sluzbenik == null){
            return false;
        }
        sluzbenikRepository.delete(sluzbenik);
        return true;
    }
}
