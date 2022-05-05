package com.example.SluzbaZaposljavanja.service.impl;

import com.example.SluzbaZaposljavanja.model.Sluzbenik;
import com.example.SluzbaZaposljavanja.repository.SluzbenikRepository;
import com.example.SluzbaZaposljavanja.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SluzbenikServiceImpl implements SluzbenikService {

    @Autowired
    private SluzbenikRepository sluzbenikRepository;

    @Override
    public List<Sluzbenik> findAll() {
        return sluzbenikRepository.findAll();
    }

    @Override
    public Sluzbenik findOne(Integer id) {
        return sluzbenikRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        sluzbenikRepository.deleteById(id);

    }

    @Override
    public Sluzbenik save(Sluzbenik sluzbenik) {
        sluzbenikRepository.save(sluzbenik);
        return sluzbenik;
    }
}
