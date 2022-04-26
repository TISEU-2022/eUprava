package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajDTO;

public interface IzvestajService {

    IzvestajDTO getOne(Long id);
    Long createIzvestaj(IzvestajDTO izvestajDTO);
    void updateIzvestaj(Long id, IzvestajDTO izvestajDTO);
    boolean deleteIzvestaj(Long id);

}
