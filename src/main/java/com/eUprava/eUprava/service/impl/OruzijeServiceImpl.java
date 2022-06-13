package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.model.dto.OruzijeDTO;
import com.eUprava.eUprava.model.entity.Oruzije;
import com.eUprava.eUprava.payload.OruzijePostRequest;
import com.eUprava.eUprava.repository.OruzijeRepository;
import com.eUprava.eUprava.service.OruzijeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OruzijeServiceImpl implements OruzijeService {

    private final OruzijeRepository oruzijeRepository;

    public OruzijeServiceImpl(OruzijeRepository oruzijeRepository){
        this.oruzijeRepository = oruzijeRepository;
    }

    @Override
    public Oruzije findById(Long oruzijeId) {
        return  oruzijeRepository.findById(oruzijeId).orElseThrow(() -> new RuntimeException("Nije pronadjen zahtev"));
    }

    @Override
    public List<Oruzije> findAll() {
        return oruzijeRepository.findAll();
    }

    @Override
    public Oruzije save(OruzijeDTO oruzijeDTO) {
        Oruzije oruzije = new Oruzije();
        oruzije.setMarkaOruzija(oruzijeDTO.getMarkaOruzija());
        oruzije.setVrstaoruzija(oruzijeDTO.getVrstaoruzija());
        oruzije.setKalibar(oruzijeDTO.getKalibar());
        oruzije.setSerijskiBroj(oruzijeDTO.getSerijskiBroj());
        oruzije.setList(oruzijeDTO.getList());
        oruzije.setZahtev(oruzijeDTO.getZahtev());

        return oruzijeRepository.save(oruzije);
    }

    @Override
    public void remove(Long id) {
        oruzijeRepository.deleteById(id);
    }

    @Override
    public Oruzije update(Long id, OruzijePostRequest oruzijePostRequest) {
        Oruzije oruzije = (Oruzije) oruzijeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Gun with id " + id + " not found"));


        oruzije.setMarkaOruzija(oruzijePostRequest.getMarkaOruzija());
        oruzije.setVrstaoruzija(oruzijePostRequest.getVrstaOruzija());
        oruzije.setKalibar(oruzijePostRequest.getKalibar());
        oruzije.setSerijskiBroj(oruzijePostRequest.getSerijskiBroj());
        oruzije.setList(oruzijePostRequest.getList());
        oruzije.setZahtev(oruzijePostRequest.getZahtev());

        return oruzijeRepository.save(oruzije);

    }
}
