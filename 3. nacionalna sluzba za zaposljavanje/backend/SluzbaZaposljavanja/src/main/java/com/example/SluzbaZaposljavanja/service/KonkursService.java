package com.example.SluzbaZaposljavanja.service;

import com.example.SluzbaZaposljavanja.model.Konkurs;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;

import java.util.List;

public interface KonkursService {

    List<Konkurs> findAll();

    Konkurs findOne(Integer id);

    void remove(Integer id);

    Konkurs save(Konkurs konkurs);

}
