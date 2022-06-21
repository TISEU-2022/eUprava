package com.example.SluzbaZaposljavanja.service;

import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.model.dto.OglasZaPosaoDto;

import java.util.List;

public interface OglasZaPosaoService {

    List<OglasZaPosao> findAll();

    OglasZaPosao findOne(Integer id);

    void remove(Integer id);

    OglasZaPosao save(OglasZaPosao oglasZaPosao);
   

}
