package com.example.SluzbaZaposljavanja.service.impl;

import com.example.SluzbaZaposljavanja.model.Gradjanin;
import com.example.SluzbaZaposljavanja.repository.GradjaninRepository;
import com.example.SluzbaZaposljavanja.service.GradjaninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradjaninServiceImpl implements GradjaninService {

    @Autowired
    private GradjaninRepository gradjaninRepository;

    @Override
    public List<Gradjanin> findAll() {
        return gradjaninRepository.findAll();
    }

    @Override
    public Gradjanin findOne(Integer id) {
        return gradjaninRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        gradjaninRepository.deleteById(id);
    }

    @Override
    public Gradjanin save(Gradjanin gradjanin) {
        return gradjaninRepository.save(gradjanin);
    }

    @Override
    public Gradjanin findByKorisnickoIme(String korisnickoIme) {
        Gradjanin gradjanin = gradjaninRepository.findByKorisnickoIme(korisnickoIme);
        if(gradjanin == null){
            return null;
        }
        return gradjanin;
    }

}
