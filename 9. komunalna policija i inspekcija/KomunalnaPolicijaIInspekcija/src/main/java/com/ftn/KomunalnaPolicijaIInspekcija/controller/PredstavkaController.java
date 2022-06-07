package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaResponseDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.PredstavkaMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Predstavka;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PodnosilacService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PredstavkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/predstavka")
public class PredstavkaController {

    @Autowired
    private PredstavkaService predstavkaService;

    @Autowired
    private PodnosilacService podnosilacService;

    @GetMapping
    public ResponseEntity<List<PredstavkaResponseDTO>> getAll(){
        return new ResponseEntity<>(predstavkaService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PredstavkaResponseDTO> findOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(PredstavkaMapper.mapDTO(predstavkaService.findOne(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<Long> createPredstavka(@ModelAttribute PredstavkaRequestDTO predstavkaRequestDTO){

        Predstavka predstavka = PredstavkaMapper.mapModel(predstavkaRequestDTO);
        predstavka.setVremePodnosenja(new Date());

        podnosilacService.create(predstavka.getPodnosilac());
        predstavka = predstavkaService.save(predstavka);

        return new ResponseEntity<>(predstavka.getId(), HttpStatus.CREATED);
    }
}
