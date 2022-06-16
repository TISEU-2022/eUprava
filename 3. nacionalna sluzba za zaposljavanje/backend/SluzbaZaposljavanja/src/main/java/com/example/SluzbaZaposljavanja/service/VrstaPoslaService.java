package com.example.SluzbaZaposljavanja.service;

import com.example.SluzbaZaposljavanja.model.Sluzbenik;
import com.example.SluzbaZaposljavanja.model.VrstaPosla;

import java.util.List;

public interface VrstaPoslaService {

    List<VrstaPosla> findAll();

    VrstaPosla findOne(Integer id);

    void remove(Integer id);

    VrstaPosla save(VrstaPosla vrstaPosla);


}
