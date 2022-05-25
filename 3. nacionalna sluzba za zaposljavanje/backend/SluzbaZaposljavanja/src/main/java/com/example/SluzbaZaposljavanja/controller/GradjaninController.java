package com.example.SluzbaZaposljavanja.controller;

import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.model.Gradjanin;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.service.GradjaninService;
import com.example.SluzbaZaposljavanja.service.OglasZaPosaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(value = "api/gradjani")
public class GradjaninController {

    @Autowired
    private GradjaninService gradjaninService;

    @GetMapping
    public ResponseEntity<List<Gradjanin>> getGradjane(){
        List<Gradjanin> gradjani = gradjaninService.findAll();
        return new ResponseEntity<>(gradjani, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Gradjanin> getSemesters(@PathVariable("id") Integer id){
        Gradjanin gradjanin = gradjaninService.findOne(id);
        if(gradjanin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gradjanin, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Gradjanin> save(@RequestBody Gradjanin gradjanin){
        Gradjanin newGradjanin = gradjaninService.save(gradjanin);
        return ResponseEntity.status(201).body(newGradjanin);
    }

    @GetMapping(value = "zaposlenje/{korisnickoIme}")
    public ResponseEntity<Boolean> getStatusZaposlenjaGradjanina(@PathVariable("korisnickoIme") String korisnickoIme){
        Gradjanin gradjanin = gradjaninService.findByKorisnickoIme(korisnickoIme);
        Firma firma = gradjanin.getFirma();
        boolean zaposlenje;
        if(firma == null) {
            zaposlenje = false;
        }else{
            zaposlenje = true;
        }
        if(gradjanin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Boolean>(zaposlenje, HttpStatus.OK);
    }

}
