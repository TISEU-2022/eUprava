package com.ftn.KomunalnaPolicijaIInspekcija.service.implementation;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import com.ftn.KomunalnaPolicijaIInspekcija.repository.PodnosilacRepository;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PodnosilacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodnosilacServiceImpl implements PodnosilacService {

    @Autowired
    private PodnosilacRepository podnosilacRepository;

    @Override
    public List<Podnosilac> getAll() {
        return podnosilacRepository.findAll();
    }

    @Override
    public Podnosilac getOne(Long id) {
        return podnosilacRepository.findById(id).orElse(null);
    }

    @Override
    public Long createPodnosilac(Podnosilac podnosilac) {
        return podnosilacRepository.save(podnosilac).getId();
    }

    @Override
    public Podnosilac updatePodnosilac(Podnosilac podnosilac) {
        return podnosilacRepository.save(podnosilac);
    }

    @Override
    public void deletePodnosilac(Long id) {
        podnosilacRepository.deletePodnosilacById(id);
    }
}
