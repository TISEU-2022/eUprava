package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajDTO;

public interface IzvestajService {

    IzvestajDTO getOne(Long id);
    Long createIzvestaj(IzvestajDTO izvestajDTO);
    void updateIzvestaj(IzvestajDTO izvestajDTO);
    void deleteIzvestaj(Long id);

}
