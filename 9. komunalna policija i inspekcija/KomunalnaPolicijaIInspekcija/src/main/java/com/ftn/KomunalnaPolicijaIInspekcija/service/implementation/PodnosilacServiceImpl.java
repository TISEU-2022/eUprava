package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.PodnosilacRepository;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PodnosilacService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class PodnosilacServiceImpl implements PodnosilacService {

    @Autowired
    private PodnosilacRepository podnosilacRepository;

    @Override
    public List<Podnosilac> getAll() {
        return podnosilacRepository.findAll();
    }

    @Override
    public Podnosilac getOne(UUID id) {
        return podnosilacRepository.findPodnosilacById(id);
    }

    @Override
    public UUID createPodnosilac(Podnosilac podnosilac) {
        return podnosilacRepository.save(podnosilac).getId();
    }

    @Override
    public Podnosilac updatePodnosilac(Podnosilac podnosilac) {
        return podnosilacRepository.save(podnosilac);
    }

    @Override
    public void deletePodnosilac(UUID id) {
        podnosilacRepository.deletePodnosilacById(id);
    }
}
