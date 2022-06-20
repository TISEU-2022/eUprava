package com.example.SluzbaZaposljavanja.service.impl;

import com.example.SluzbaZaposljavanja.model.ZahtevEvidencije;
import com.example.SluzbaZaposljavanja.repository.ZahtevEvidencijeRepository;
import com.example.SluzbaZaposljavanja.service.ZahtevEvidencijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZahtevEvidencijeServiceImpl implements ZahtevEvidencijeService {

    @Autowired
    private ZahtevEvidencijeRepository zahtevEvidencijeRepository;

    @Override
    public List<ZahtevEvidencije> findAll() {
        return zahtevEvidencijeRepository.findAll();
    }

    @Override
    public ZahtevEvidencije findOne(Integer id) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public ZahtevEvidencije save(ZahtevEvidencije zahtevEvidencije) {
        return null;
    }
}
