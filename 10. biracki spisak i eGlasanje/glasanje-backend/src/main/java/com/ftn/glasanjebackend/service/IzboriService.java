package com.ftn.glasanjebackend.service;

import com.ftn.glasanjebackend.model.Izbori;
import com.ftn.glasanjebackend.repository.IzboriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IzboriService {

    @Autowired
    IzboriRepository izboriRepository;

    public List<Izbori> findAll(){
        return izboriRepository.findAll();
    }
    public Izbori findOne(Long id) {
        return izboriRepository.findById(id).orElse(null);
    }

    public Izbori save(Izbori izbori){
        return  izboriRepository.save(izbori);
    }
}
