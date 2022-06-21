package com.example.SluzbaZaposljavanja.controller;

import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.model.VrstaPosla;
import com.example.SluzbaZaposljavanja.service.FirmaService;
import com.example.SluzbaZaposljavanja.service.VrstaPoslaService;
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
@RequestMapping(value = "api/poslovi")
@CrossOrigin(origins="*")
public class VrstaPoslaController {

    @Autowired
    private VrstaPoslaService vrstaPoslaService;

    @GetMapping
    public ResponseEntity<List<VrstaPosla>> getVrstePoslova(){
        List<VrstaPosla> vrstePoslova = vrstaPoslaService.findAll();
        return new ResponseEntity<>(vrstePoslova, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VrstaPosla> getVrstuPosla(@PathVariable("id") Integer id) {
        VrstaPosla vrstaPosla = vrstaPoslaService.findOne(id);
        if (vrstaPosla == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vrstaPosla, HttpStatus.OK);
    }

}
