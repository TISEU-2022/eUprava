package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.IzvestajMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Izvestaj;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.IzvestajRepository;
import com.ftn.KomunalnaPolicijaIInspekcija.service.IzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IzvestajServiceImpl implements IzvestajService {

    @Autowired
    private IzvestajRepository izvestajRepository;

    @Override
    public IzvestajDTO getOne(Long id) throws NotFoundException {
        Izvestaj izvestaj = izvestajRepository.findIzvestajById(id);
        if(izvestaj == null){
            throw new NotFoundException("Izvestaj not found with this id: " + id);
        }
        return IzvestajMapper.mapDTO(izvestaj);
    }

    @Override
    public Long createIzvestaj(IzvestajDTO izvestajDTO) {
        Izvestaj izvestaj = izvestajRepository.save(IzvestajMapper.mapModel(izvestajDTO));
        return izvestaj.getId();
    }

    @Override
    public void updateIzvestaj(Long id, IzvestajDTO izvestajDTO) throws NotFoundException {
        Izvestaj izvestaj = izvestajRepository.findIzvestajById(id);
        if(izvestaj == null){
            throw new NotFoundException("Izvestaj not found with this id: " + id);
        }
        izvestajDTO.setId(id);
        izvestajRepository.save(IzvestajMapper.mapModel(izvestajDTO));
    }

    @Override
    public boolean deleteIzvestaj(Long id) {
        Izvestaj izvestaj = izvestajRepository.findIzvestajById(id);
        if(izvestaj == null){
            return false;
        }
        izvestajRepository.delete(izvestaj);
        return true;
    }

    @Override
    public Izvestaj save(Izvestaj izvestaj) {
        return izvestajRepository.save(izvestaj);
    }
}
