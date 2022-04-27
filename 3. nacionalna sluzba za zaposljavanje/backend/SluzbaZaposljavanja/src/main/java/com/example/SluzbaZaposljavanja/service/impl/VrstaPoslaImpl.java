package com.example.SluzbaZaposljavanja.service.impl;

import com.example.SluzbaZaposljavanja.model.VrstaPosla;
import com.example.SluzbaZaposljavanja.repository.VrstaPoslaRepository;
import com.example.SluzbaZaposljavanja.service.VrstaPoslaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VrstaPoslaImpl implements VrstaPoslaService {

    @Autowired
    private VrstaPoslaRepository vrstaPoslaRepository;

    @Override
    public List<VrstaPosla> findAll() {
        return vrstaPoslaRepository.findAll();
    }

    @Override
    public VrstaPosla findOne(Integer id) {
        return vrstaPoslaRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public VrstaPosla save(VrstaPosla vrstaPosla) {
        vrstaPoslaRepository.save(vrstaPosla);
        return vrstaPosla;
    }
}
