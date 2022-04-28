package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.SluzbenikDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.SluzbenikMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.SluzbenikRepository;
import com.ftn.KomunalnaPolicijaIInspekcija.service.SluzbenikService;
import org.springframework.stereotype.Service;

@Service
public class SluzbenikServiceImpl implements SluzbenikService {

    private final SluzbenikRepository sluzbenikRepository;

    public SluzbenikServiceImpl(SluzbenikRepository sluzbenikRepository){
        this.sluzbenikRepository = sluzbenikRepository;
    }

    @Override
    public SluzbenikDTO getOne(Long id) throws NotFoundException {
        Sluzbenik sluzbenik = sluzbenikRepository.findSluzbenikById(id);
        if(sluzbenik == null){
            throw new NotFoundException("Nema sluzbenika sa id-om: " + id);
        }
        return SluzbenikMapper.mapDTO(sluzbenik);
    }

    @Override
    public Long createSluzbenik(SluzbenikDTO sluzbenikDTO) {
        Sluzbenik sluzbenik = new Sluzbenik(sluzbenikDTO.getIme(), sluzbenikDTO.getPrezime(), sluzbenikDTO.getEmail(), sluzbenikDTO.getJmbg());
        return sluzbenikRepository.save(sluzbenik).getId();
    }

    @Override
        public void updateSluzbenik(Long id, SluzbenikDTO sluzbenikDTO) throws NotFoundException{
        Sluzbenik sluzbenik = sluzbenikRepository.findSluzbenikById(id);
        if (sluzbenik == null){
            throw new NotFoundException("Ne postoji sluzbenik sa id-om: " + id);
        }
        sluzbenikDTO.setId(id);
        sluzbenikRepository.save(SluzbenikMapper.mapModel(sluzbenikDTO));
    }

    @Override
    public boolean deleteSluzbenik(Long id) {
        Sluzbenik sluzbenik = sluzbenikRepository.findSluzbenikById(id);
        if(sluzbenik == null){
            return false;
        }
        sluzbenikRepository.delete(sluzbenik);
        return true;
    }
}
