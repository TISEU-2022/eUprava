package com.example.SluzbaZaposljavanja.controller;

import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.model.Gradjanin;
import com.example.SluzbaZaposljavanja.service.FirmaService;
import com.example.SluzbaZaposljavanja.service.GradjaninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/firme")
@CrossOrigin(origins="*")
public class FirmaController {

    @Autowired
    private FirmaService firmaService;

    @GetMapping
    public ResponseEntity<List<Firma>> getFirme(){
        List<Firma> firme = firmaService.findAll();
        return new ResponseEntity<>(firme, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Firma> getFirma(@PathVariable("id") Integer id) {
        Firma firma = firmaService.findOne(id);
        if (firma == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(firma, HttpStatus.OK);
    }

}
