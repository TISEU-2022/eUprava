package com.example.SluzbaZaposljavanja.controller;

import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.model.ZahtevEvidencije;
import com.example.SluzbaZaposljavanja.service.OglasZaPosaoService;
import com.example.SluzbaZaposljavanja.service.ZahtevEvidencijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/zahtevi-evidencije")
public class ZahtevEvidencijeController {

    @Autowired
    private ZahtevEvidencijeService zahtevEvidencijeService;

    @GetMapping
    public ResponseEntity<List<ZahtevEvidencije>> getZahteveEvidencije(){
        List<ZahtevEvidencije> zahteviEvidencije = zahtevEvidencijeService.findAll();
        return new ResponseEntity<>(zahteviEvidencije, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ZahtevEvidencije> getZahtevEvidencije(@PathVariable("id") Integer id) {
        ZahtevEvidencije zahtevEvidencije = zahtevEvidencijeService.findOne(id);
        if (zahtevEvidencije == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(zahtevEvidencije, HttpStatus.OK);
    }

}
