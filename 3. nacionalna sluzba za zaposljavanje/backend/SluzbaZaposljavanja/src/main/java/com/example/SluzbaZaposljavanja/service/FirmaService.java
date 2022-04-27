package com.example.SluzbaZaposljavanja.service;

import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.model.Gradjanin;

import java.util.List;

public interface FirmaService {

    List<Firma> findAll();

    Firma findOne(Integer id);

    void remove(Integer id);

    Firma save(Firma firma);

}
