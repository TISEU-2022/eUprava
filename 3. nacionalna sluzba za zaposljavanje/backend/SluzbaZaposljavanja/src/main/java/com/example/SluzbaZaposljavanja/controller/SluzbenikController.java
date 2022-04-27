package com.example.SluzbaZaposljavanja.controller;

import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.model.Sluzbenik;
import com.example.SluzbaZaposljavanja.service.KonkursService;
import com.example.SluzbaZaposljavanja.service.OglasZaPosaoService;
import com.example.SluzbaZaposljavanja.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "api/sluzbenici")
public class SluzbenikController {

    @Autowired
    private SluzbenikService sluzbenikService;

    @GetMapping
    public ResponseEntity<List<Sluzbenik>> getSluzbenike(){
        List<Sluzbenik> sluzbenici = sluzbenikService.findAll();
        return new ResponseEntity<>(sluzbenici, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Sluzbenik> getSluzbenika(@PathVariable("id") Integer id) {
        Sluzbenik sluzbenik = sluzbenikService.findOne(id);
        if (sluzbenik == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(sluzbenik, HttpStatus.OK);
    }

}
