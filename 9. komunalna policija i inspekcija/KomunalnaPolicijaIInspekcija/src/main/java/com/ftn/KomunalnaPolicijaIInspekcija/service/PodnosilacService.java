package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;

import java.util.List;

public interface PodnosilacService {

    List<Podnosilac> getAll();
    Podnosilac getOne(Long id);
    Podnosilac getOneByJmbg(String jmbg);
    Long create(Podnosilac podnosilac);
}
