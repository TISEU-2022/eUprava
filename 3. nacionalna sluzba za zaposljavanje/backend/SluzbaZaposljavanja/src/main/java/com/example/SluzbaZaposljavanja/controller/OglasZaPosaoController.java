package com.example.SluzbaZaposljavanja.controller;

import com.example.SluzbaZaposljavanja.model.Firma;
import com.example.SluzbaZaposljavanja.model.OglasZaPosao;
import com.example.SluzbaZaposljavanja.model.dto.OglasZaPosaoDto;
import com.example.SluzbaZaposljavanja.service.FirmaService;
import com.example.SluzbaZaposljavanja.service.OglasZaPosaoService;
import com.example.SluzbaZaposljavanja.service.VrstaPoslaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api/oglasi")
@CrossOrigin(origins="*")

public class OglasZaPosaoController {

    @Autowired
    private OglasZaPosaoService oglasZaPosaoService;
    
    @Autowired
    private FirmaService firmaService;
    
    @Autowired
    private VrstaPoslaService vrstaPoslaService;

    @GetMapping
    public ResponseEntity<List<OglasZaPosao>> getOglaseZaPosao(){
        List<OglasZaPosao> oglasi = oglasZaPosaoService.findAll();
        return new ResponseEntity<>(oglasi, HttpStatus.OK);
    }

    @PostMapping
    public void save(@RequestBody OglasZaPosaoDto oglasZaPosao){
        OglasZaPosao oglas = new OglasZaPosao();
        oglas.setNaziv(oglasZaPosao.getNaziv());
        oglas.setOpis(oglasZaPosao.getOpis());
        oglas.setDatumOd(oglasZaPosao.getDatumOd());
        oglas.setDatumDo(oglasZaPosao.getDatumDo());
        oglas.setFirma(firmaService.findOne(oglasZaPosao.getFirma()));
        oglas.setVrstaPosla(vrstaPoslaService.findOne(oglasZaPosao.getVrstaPosla()));
    	
    	oglasZaPosaoService.save(oglas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OglasZaPosao> getOglasZaPosao(@PathVariable("id") Integer id) {
        OglasZaPosao oglasZaPosao = oglasZaPosaoService.findOne(id);
        if (oglasZaPosao == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(oglasZaPosao, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public void update(@PathVariable("id") Integer id, @RequestBody OglasZaPosaoDto oglasZaPosao){
        OglasZaPosao oglas = oglasZaPosaoService.findOne(id);
        oglas.setId(oglasZaPosao.getId());
        oglas.setNaziv(oglasZaPosao.getNaziv());
        oglas.setOpis(oglasZaPosao.getOpis());
        oglas.setDatumOd(oglasZaPosao.getDatumOd());
        oglas.setDatumDo(oglasZaPosao.getDatumDo());
        oglas.setFirma(firmaService.findOne(oglasZaPosao.getFirma()));
        oglas.setVrstaPosla(vrstaPoslaService.findOne(oglasZaPosao.getVrstaPosla()));
    	
    	oglasZaPosaoService.save(oglas);
    }



}
