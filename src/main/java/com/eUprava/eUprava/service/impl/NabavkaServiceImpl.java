package com.eUprava.eUprava.service.impl;

import com.eUprava.eUprava.model.dto.NabavkaDTO;
import com.eUprava.eUprava.model.entity.ZahtevZaNabavku;
import com.eUprava.eUprava.payload.NabavkaPostRequest;
import com.eUprava.eUprava.repository.NabavkaRepository;
import com.eUprava.eUprava.service.NabavkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NabavkaServiceImpl implements NabavkaService {

    private final NabavkaRepository nabavkaRepository;

    public NabavkaServiceImpl(NabavkaRepository nabavkaRepository){
        this.nabavkaRepository = nabavkaRepository;
    }

    @Override
    public ZahtevZaNabavku findOne(Long nabavkaId) {
        return (ZahtevZaNabavku) nabavkaRepository.findOne(nabavkaId).orElse(null);
    }

    @Override
    public List<ZahtevZaNabavku> findAll() {
        return nabavkaRepository.findAll();
    }

    @Override
    public ZahtevZaNabavku save(NabavkaDTO nabavkaDTO) {
        ZahtevZaNabavku zahtevZaNabavku = new ZahtevZaNabavku();
        zahtevZaNabavku.setVazecaLicna(nabavkaDTO.getVazecaLicna());
        zahtevZaNabavku.setSudskoUverenje(nabavkaDTO.getSudskoUverenje());
        zahtevZaNabavku.setKolicina(nabavkaDTO.getKolicina());
        zahtevZaNabavku.setLekarskoUverenje(nabavkaDTO.getLekarskoUverenje());
        zahtevZaNabavku.setOruzije(nabavkaDTO.getOruzije());

        return nabavkaRepository.save(zahtevZaNabavku);
    }

    @Override
    public void remove(Long id) {
        nabavkaRepository.deleteById(id);
    }

    @Override
    public ZahtevZaNabavku update(Long id, NabavkaPostRequest nabavkaPostRequest) {
        ZahtevZaNabavku zahtevZaNabavku = (ZahtevZaNabavku) nabavkaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Gun with id " + id + " not found"));


        zahtevZaNabavku.setVazecaLicna(nabavkaPostRequest.getVazecaLicna());
        zahtevZaNabavku.setSudskoUverenje(nabavkaPostRequest.getSudskoUverenje());
        zahtevZaNabavku.setKolicina(nabavkaPostRequest.getKolicina());
        zahtevZaNabavku.setLekarskoUverenje(nabavkaPostRequest.getLekarskoUverenje());
        zahtevZaNabavku.setOruzije(nabavkaPostRequest.getOruzije());

        return nabavkaRepository.save(zahtevZaNabavku);
    }
}
