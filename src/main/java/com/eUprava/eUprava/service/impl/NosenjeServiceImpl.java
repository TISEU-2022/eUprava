package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.model.dto.NosenjeDTO;
import com.eUprava.eUprava.model.entity.ZahtevZaNosenje;
import com.eUprava.eUprava.payload.NosenjePostRequest;
import com.eUprava.eUprava.repository.NosenjeRepository;
import com.eUprava.eUprava.service.NosenjeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NosenjeServiceImpl implements NosenjeService {
    private final NosenjeRepository nosenjeRepository;

    public NosenjeServiceImpl(NosenjeRepository nosenjeRepository){
        this.nosenjeRepository = nosenjeRepository;
    }

    @Override
    public ZahtevZaNosenje findById(Long nosenje_id) {
        return  nosenjeRepository.findById(nosenje_id).orElseThrow(() -> new RuntimeException("Nije pronadjen zahtev"));
    }

    @Override
    public List<ZahtevZaNosenje> findAll() {
        return nosenjeRepository.findAll();
    }

    @Override
    public ZahtevZaNosenje save(NosenjeDTO nosenjeDTO) {
        ZahtevZaNosenje zahtevZaNosenje = new ZahtevZaNosenje();
        zahtevZaNosenje.setVazecaLicna(nosenjeDTO.getVazecaLicna());
        zahtevZaNosenje.setSudskoUverenje(nosenjeDTO.getSudskoUverenje());
        zahtevZaNosenje.setList(nosenjeDTO.getList());
        return nosenjeRepository.save(zahtevZaNosenje);
    }

    @Override
    public void remove(Long nosenje_id) {
        nosenjeRepository.deleteById(nosenje_id);
    }

    @Override
    public ZahtevZaNosenje update(Long nosenje_id, NosenjePostRequest nosenjePostRequest) {
        ZahtevZaNosenje zahtevZaNosenje = (ZahtevZaNosenje) nosenjeRepository.findById(nosenje_id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Gun with id " + nosenje_id + " not found"));


        zahtevZaNosenje.setVazecaLicna(nosenjePostRequest.getVazecaLicna());
        zahtevZaNosenje.setSudskoUverenje(nosenjePostRequest.getSudskoUverenje());
        zahtevZaNosenje.setList(nosenjePostRequest.getList());
        return nosenjeRepository.save(zahtevZaNosenje);
    }
}
