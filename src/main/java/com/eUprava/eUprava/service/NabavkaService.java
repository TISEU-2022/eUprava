package com.eUprava.eUprava.service;

import com.eUprava.eUprava.model.dto.NabavkaDTO;
import com.eUprava.eUprava.model.entity.ZahtevZaNabavku;
import com.eUprava.eUprava.payload.NabavkaPostRequest;

import java.util.List;

public interface NabavkaService {

    ZahtevZaNabavku findOne(Long nabavkaId);
    List<ZahtevZaNabavku> findAll();
    ZahtevZaNabavku save(NabavkaDTO nabavkaDTO);
    void remove (Long id);
    ZahtevZaNabavku update(Long id, NabavkaPostRequest nabavkaPostRequest);
}
