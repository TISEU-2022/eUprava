package com.example.SluzbaZaposljavanja.service.impl;

import com.example.SluzbaZaposljavanja.model.Konkurs;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.repository.KonkursRepository;
import com.example.SluzbaZaposljavanja.service.KonkursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KonkursServiceImpl implements KonkursService {

    @Autowired
    private KonkursRepository konkursRepository;

    @Override
    public List<Konkurs> findAll() {
        return konkursRepository.findAll();
    }

    @Override
    public Konkurs findOne(Integer id) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public Konkurs save(Konkurs konkurs) {
        konkursRepository.save(konkurs);
        return  konkurs;
    }
}
