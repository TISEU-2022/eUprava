package com.example.SluzbaZaposljavanja.service;

import com.example.SluzbaZaposljavanja.model.Gradjanin;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;

import java.util.List;

public interface GradjaninService {

    List<Gradjanin> findAll();

    Gradjanin findOne(Integer id);

    void remove(Integer id);

    Gradjanin save(Gradjanin gradjanin);

}
