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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api/konkursi")
@CrossOrigin(origins="http://localhost:3000")

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
    
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<Konkurs> getKonkursByUser(@PathVariable("userId") Integer userId) {
    	List<Konkurs> konkursi = konkursService.findAll();
        for (Konkurs konkurs2 : konkursi) {
        	if(konkurs2.getGradjanin().getId() != userId) {
        		return new ResponseEntity<>(konkurs2, HttpStatus.OK);
        	}
		}
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @PostMapping()
    public ResponseEntity<Konkurs> saveKonkurs(@RequestBody Konkurs konkurs){
        konkurs = konkursService.save(konkurs);
        return new ResponseEntity<>(konkurs, HttpStatus.OK);


    }

}
