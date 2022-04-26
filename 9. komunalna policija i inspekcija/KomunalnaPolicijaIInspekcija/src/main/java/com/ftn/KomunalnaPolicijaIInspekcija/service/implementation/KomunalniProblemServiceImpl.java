package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.service.KomunalniProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.KomunalniProblemRepository;

import java.util.List;
import java.util.UUID;

public class KomunalniProblemServiceImpl implements KomunalniProblemService {

    @Autowired
    private KomunalniProblemRepository komunalniProblemRepository;

    @Override
    public List<KomunalniProblem> getAll() {
        return komunalniProblemRepository.findAll();
    }

    @Override
    public KomunalniProblem getOne(UUID id) {
        return komunalniProblemRepository.findById(id).orElse(null);
    }

    @Override
    public UUID createKomunalniProblem(KomunalniProblem komunalniProblem) {
        return null;
    }

    @Override
    public KomunalniProblem updateKomunalniProblem(KomunalniProblem komunalniProblem) {
        return null;
    }

    @Override
    public void deleteKomunalniProblem(UUID id) {

    }
}
