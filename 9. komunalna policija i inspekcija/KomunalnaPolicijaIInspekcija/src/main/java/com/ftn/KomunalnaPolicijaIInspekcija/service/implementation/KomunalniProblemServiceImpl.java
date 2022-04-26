package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.service.KomunalniProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.KomunalniProblemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KomunalniProblemServiceImpl implements KomunalniProblemService {

    @Autowired
    private KomunalniProblemRepository komunalniProblemRepository;

    @Override
    public List<KomunalniProblem> getAll() {
        return komunalniProblemRepository.findAll();
    }

    @Override
    public KomunalniProblem getOne(Long id) {
        return komunalniProblemRepository.findKomunalniProblemById(id);
    }

    @Override
    public Long createKomunalniProblem(KomunalniProblem komunalniProblem) {
        return komunalniProblemRepository.save(komunalniProblem).getId();
    }

    @Override
    public KomunalniProblem updateKomunalniProblem(KomunalniProblem komunalniProblem) {
        return komunalniProblemRepository.save(komunalniProblem);
    }

    @Override
    public void deleteKomunalniProblem(Long id) {
        komunalniProblemRepository.deleteKomunalniProblemById(id);
    }
}
