package com.example.SluzbaZaposljavanja.service.impl;

import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.repository.OglasZaPosaoRepository;
import com.example.SluzbaZaposljavanja.service.OglasZaPosaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OglasZaPosaoServiceImpl implements OglasZaPosaoService {

    @Autowired
    private OglasZaPosaoRepository oglasZaPosaoRepository;

    @Override
    public List<OglasZaPosao> findAll() {
        return oglasZaPosaoRepository.findAll();
    }

    @Override
    public OglasZaPosao findOne(Integer id) {
        return oglasZaPosaoRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        oglasZaPosaoRepository.deleteById(id);
    }

    @Override
    public OglasZaPosao save(OglasZaPosao oglasZaPosao) {
        oglasZaPosaoRepository.save(oglasZaPosao);
        return oglasZaPosao;
    }
}
