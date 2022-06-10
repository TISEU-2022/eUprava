package com.example.SluzbaZaposljavanja.service.impl;

import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.repository.FirmaRepository;
import com.example.SluzbaZaposljavanja.service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmaServiceImpl implements FirmaService {

    @Autowired
    private FirmaRepository firmaRepository;

    @Override
    public List<Firma> findAll() {
        return firmaRepository.findAll();
    }

    @Override
    public Firma findOne(Integer id) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public Firma save(Firma firma) {
        return null;
    }
}
