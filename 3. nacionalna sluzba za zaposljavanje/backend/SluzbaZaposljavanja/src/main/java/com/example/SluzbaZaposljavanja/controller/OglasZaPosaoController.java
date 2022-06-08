package com.example.SluzbaZaposljavanja.controller;

import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.service.OglasZaPosaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api/oglasi")
<<<<<<< HEAD
<<<<<<< HEAD
=======
@CrossOrigin(origins="http://localhost:3000")
>>>>>>> zaposljavanje-init
=======
@CrossOrigin(origins="http://localhost:3000")
>>>>>>> 9aa1fa015c0a422bba3229b3ff6d5876c08f41ad
public class OglasZaPosaoController {

    @Autowired
    private OglasZaPosaoService oglasZaPosaoService;

    @GetMapping
    public ResponseEntity<List<OglasZaPosao>> getOglaseZaPosao(){
        List<OglasZaPosao> oglasi = oglasZaPosaoService.findAll();
        return new ResponseEntity<>(oglasi, HttpStatus.OK);
    }

    @PostMapping
    public void save(@RequestBody OglasZaPosao oglasZaPosao){
        oglasZaPosaoService.save(oglasZaPosao);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OglasZaPosao> getOglasZaPosao(@PathVariable("id") Integer id) {
        OglasZaPosao oglasZaPosao = oglasZaPosaoService.findOne(id);
        if (oglasZaPosao == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(oglasZaPosao, HttpStatus.OK);
    }



}
