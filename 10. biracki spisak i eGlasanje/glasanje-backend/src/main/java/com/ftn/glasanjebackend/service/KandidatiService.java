package com.ftn.glasanjebackend.service;

import com.ftn.glasanjebackend.model.Kandidat;
import com.ftn.glasanjebackend.repository.KandidatiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KandidatiService {

    @Autowired
    KandidatiRepository kandidatiRepository;

    public List<Kandidat> findAll(){
        return kandidatiRepository.findAll();
    }
    public Kandidat findOne(Long id) {
        return kandidatiRepository.findById(id).orElse(null);
    }
}
