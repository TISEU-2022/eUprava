package com.ftn.KomunalnaPolicijaIInspekcija.service;

import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;

import java.util.List;
import java.util.UUID;

public interface KomunalniProblemService {

    List<KomunalniProblem> getAll();
    List<KomunalniProblem> getByPodnosilac(Podnosilac podnosilac);
    KomunalniProblem getOne(Long id);
    Long createKomunalniProblem(KomunalniProblem komunalniProblem);
    KomunalniProblem updateKomunalniProblem(KomunalniProblem komunalniProblem);
    void deleteKomunalniProblem(Long id);
}
