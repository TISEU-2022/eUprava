package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.model.dto.KorisnikDTO;
import com.eUprava.eUprava.model.dto.NosenjeDTO;
import com.eUprava.eUprava.model.entity.ZahtevZaNosenje;
import com.eUprava.eUprava.payload.NosenjePostRequest;
import com.eUprava.eUprava.repository.NosenjeRepository;
import com.eUprava.eUprava.service.NosenjeService;
import com.eUprava.eUprava.service.OruzniListService;
import com.eUprava.eUprava.service.UverenjaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service

public class NosenjeServiceImpl implements NosenjeService {
    private final NosenjeRepository nosenjeRepository;
    @Autowired
    private UverenjaService uverenjaService;
    @Autowired
    private UserService userService;
    @Autowired
    private OruzniListService oruzniListService;
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
    public ZahtevZaNosenje save(String token, NosenjeDTO nosenjeDTO) throws Exception {
        KorisnikDTO korisnik= userService.myProfile(token);
        ZahtevZaNosenje zahtevZaNosenje = new ZahtevZaNosenje();
        zahtevZaNosenje.setVazecaLicna(uverenjaService.hasVazecaLicna(korisnik.getIme(),korisnik.getPrezime(),korisnik.getJmbg()));
        zahtevZaNosenje.setSudskoUverenje(uverenjaService.hasSudskoUverenje(korisnik.getIme(),korisnik.getPrezime(),korisnik.getJmbg()));
        zahtevZaNosenje.setList(oruzniListService.findById(nosenjeDTO.getList_id()));
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
