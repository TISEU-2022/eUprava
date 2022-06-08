package com.example.SluzbaZaposljavanja.controller;

import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.model.Konkurs;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.service.KonkursService;
import com.example.SluzbaZaposljavanja.service.OglasZaPosaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> zaposljavanje-init
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> 9aa1fa015c0a422bba3229b3ff6d5876c08f41ad
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "api/konkursi")
<<<<<<< HEAD
<<<<<<< HEAD
=======
@CrossOrigin(origins="http://localhost:3000")

>>>>>>> zaposljavanje-init
=======
@CrossOrigin(origins="http://localhost:3000")
>>>>>>> 9aa1fa015c0a422bba3229b3ff6d5876c08f41ad
public class KonkursController {

    @Autowired
    private KonkursService konkursService;

    @GetMapping
    public ResponseEntity<List<Konkurs>> getKonkurse(){
        List<Konkurs> konkursi = konkursService.findAll();
        return new ResponseEntity<>(konkursi, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Konkurs> getKonkurs(@PathVariable("id") Integer id) {
        Konkurs konkurs = konkursService.findOne(id);
        if (konkurs == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(konkurs, HttpStatus.OK);
    }

}
