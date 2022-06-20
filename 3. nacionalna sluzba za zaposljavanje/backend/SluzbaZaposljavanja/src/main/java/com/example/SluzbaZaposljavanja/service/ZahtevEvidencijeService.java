package com.example.SluzbaZaposljavanja.service;

import com.example.SluzbaZaposljavanja.model.Sluzbenik;
import com.example.SluzbaZaposljavanja.model.ZahtevEvidencije;

import java.util.List;

public interface ZahtevEvidencijeService {

    List<ZahtevEvidencije> findAll();

    ZahtevEvidencije findOne(Integer id);

    void remove(Integer id);

    ZahtevEvidencije save(ZahtevEvidencije zahtevEvidencije);

}
