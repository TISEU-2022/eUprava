package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;

import java.util.List;
import java.util.UUID;

public interface PodnosilacService {

    List<Podnosilac> getAll();
    Podnosilac getOne(UUID id);
    UUID createPodnosilac(Podnosilac podnosilac);
    Podnosilac updatePodnosilac(Podnosilac odnosilac);
    void deletePodnosilac(UUID id);

}
