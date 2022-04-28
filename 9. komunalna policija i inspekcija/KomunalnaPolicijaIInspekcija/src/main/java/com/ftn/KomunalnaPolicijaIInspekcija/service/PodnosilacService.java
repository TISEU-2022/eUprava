package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;

import java.util.List;

public interface PodnosilacService {

    List<Podnosilac> getAll();
    Podnosilac getOne(Long id);
    Long createPodnosilac(Podnosilac podnosilac);
    Podnosilac updatePodnosilac(Podnosilac odnosilac);
    void deletePodnosilac(Long id);

}
