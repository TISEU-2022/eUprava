package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Izvestaj;

public interface IzvestajService {

    IzvestajDTO getOne(Long id);
    Long createIzvestaj(IzvestajDTO izvestajDTO);
    void updateIzvestaj(Long id, IzvestajDTO izvestajDTO);
    boolean deleteIzvestaj(Long id);
    Izvestaj save(Izvestaj izvestaj);

}
