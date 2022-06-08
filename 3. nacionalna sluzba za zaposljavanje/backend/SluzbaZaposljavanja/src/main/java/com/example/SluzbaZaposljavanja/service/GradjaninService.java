package com.example.SluzbaZaposljavanja.service;

import com.example.SluzbaZaposljavanja.model.Gradjanin;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;

import java.util.List;

public interface GradjaninService {

    List<Gradjanin> findAll();

    Gradjanin findOne(Integer id);

    void remove(Integer id);

    Gradjanin save(Gradjanin gradjanin);

<<<<<<< HEAD
<<<<<<< HEAD
=======
    Gradjanin findByKorisnickoIme(String korisnickoIme);
>>>>>>> zaposljavanje-init
=======
    Gradjanin findByKorisnickoIme(String korisnickoIme);

>>>>>>> 9aa1fa015c0a422bba3229b3ff6d5876c08f41ad
}
