package com.example.SluzbaZaposljavanja.service;

import com.example.SluzbaZaposljavanja.model.Gradjanin;
import com.example.SluzbaZaposljavanja.model.Sluzbenik;

import java.util.List;

public interface SluzbenikService {

    List<Sluzbenik> findAll();

    Sluzbenik findOne(Integer id);

    void remove(Integer id);

    Sluzbenik save(Sluzbenik sluzbenik);

}
