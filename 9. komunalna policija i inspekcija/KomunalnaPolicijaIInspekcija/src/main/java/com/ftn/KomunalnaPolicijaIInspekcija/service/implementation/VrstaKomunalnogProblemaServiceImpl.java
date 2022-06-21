package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaKomunalnogProblema;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.VrstaKomunalnogProblemaRepository;
import com.ftn.KomunalnaPolicijaIInspekcija.service.VrstaKomunalnogProblemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VrstaKomunalnogProblemaServiceImpl implements VrstaKomunalnogProblemaService {

    @Autowired
    private VrstaKomunalnogProblemaRepository vrstaKomunalnogProblemaRepository;

    @Override
    public VrstaKomunalnogProblema getOne(Long id) {
        VrstaKomunalnogProblema vrstaKomunalnogProblema = vrstaKomunalnogProblemaRepository.findVrstaKomunalnogProblemaById(id);
        if (vrstaKomunalnogProblema == null){
            throw new NotFoundException("VrstaKomunalnogProblema with id {id} not found.");
        }
        return vrstaKomunalnogProblema;
    }

    @Override
    public List<VrstaKomunalnogProblema> findAll() {
        return vrstaKomunalnogProblemaRepository.findAll();
    }

    @Override
    public Long save(VrstaKomunalnogProblema vrstaKomunalnogProblema) {
        return vrstaKomunalnogProblemaRepository.save(vrstaKomunalnogProblema).getId();
    }

    @Override
    public void update(Long id, VrstaKomunalnogProblema vrstaKomunalnogProblema) {
        VrstaKomunalnogProblema vkp = vrstaKomunalnogProblemaRepository.findVrstaKomunalnogProblemaById(id);
        if (vkp == null){
            throw new NotFoundException("VrstaKomunalnogProblema with id {id} not found.");
        }
        vrstaKomunalnogProblema.setId(id);
        vrstaKomunalnogProblemaRepository.save(vrstaKomunalnogProblema);
    }

    @Override
    public boolean delete(Long id) {
        VrstaKomunalnogProblema vkp = vrstaKomunalnogProblemaRepository.findVrstaKomunalnogProblemaById(id);
        if (vkp == null){
            throw new NotFoundException("VrstaKomunalnogProblema with id {id} not found.");
        }
        vrstaKomunalnogProblemaRepository.delete(vkp);
        return true;
    }
}
