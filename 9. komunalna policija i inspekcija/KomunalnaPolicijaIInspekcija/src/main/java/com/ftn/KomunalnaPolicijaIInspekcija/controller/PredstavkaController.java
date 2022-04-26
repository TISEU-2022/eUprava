package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.VrstaPredstavkeDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.PredstavkaMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Predstavka;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PredstavkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/predstavka")
public class PredstavkaController {

    @Autowired
    private PredstavkaService predstavkaService;

    @GetMapping
    public ResponseEntity<List<PredstavkaDTO>> getAll(){
        return new ResponseEntity<>(predstavkaService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PredstavkaDTO> findOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(PredstavkaMapper.mapDTO(predstavkaService.findOne(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createPredstavka(@RequestBody PredstavkaDTO predstavkaDTO){
        Predstavka predstavka = predstavkaService.save(PredstavkaMapper.mapModel(predstavkaDTO));
        return new ResponseEntity<>(predstavka.getId(), HttpStatus.CREATED);
    }

}
