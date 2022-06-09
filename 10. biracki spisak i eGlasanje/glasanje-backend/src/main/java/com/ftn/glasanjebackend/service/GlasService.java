package com.ftn.glasanjebackend.service;

import com.ftn.glasanjebackend.model.Glas;
import com.ftn.glasanjebackend.repository.GlasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlasService {

    @Autowired
    GlasRepository glasRepository;

    public Glas save(Glas glas){
        return glasRepository.save(glas);
    }
}
