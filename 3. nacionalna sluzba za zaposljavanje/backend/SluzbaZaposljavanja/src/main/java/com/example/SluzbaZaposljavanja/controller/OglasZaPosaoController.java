package com.example.SluzbaZaposljavanja.controller;

import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.service.OglasZaPosaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "api/oglasi")
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

}
