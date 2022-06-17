package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.model.*;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PredstavkaResponseDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.PredstavkaMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.SluzbenikMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.service.IzvestajService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PodnosilacService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PredstavkaService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.SluzbenikService;
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

    @Autowired
    private SluzbenikService sluzbenikService;

    @Autowired
    private IzvestajService izvestajService;

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

    @PostMapping(value="/izvestaj/{id}")
    public ResponseEntity<?> writeIzvestaj(@PathVariable("id") Long id, @RequestBody IzvestajRequestDTO izvestajDTO){
        System.out.println(izvestajDTO);
        Predstavka predstavka = predstavkaService.findOne(id);
        Sluzbenik sluzbenik = SluzbenikMapper.mapModel(sluzbenikService.getOne(1L));
        Izvestaj novIzvestaj = new Izvestaj();
        novIzvestaj.setIzvestaj(izvestajDTO.getReport());
        novIzvestaj.setPrihvaceno(true);
        novIzvestaj.setVremePodnosenja(new Date());
        novIzvestaj.setSluzbenik(sluzbenik);
        Izvestaj vracen = izvestajService.save(novIzvestaj);
        predstavka.setIzvestaj(vracen);
        predstavkaService.save(predstavka);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="/odbaci-izvestaj/{id}")
    public ResponseEntity<?> rejectIzvestaj(@PathVariable("id") Long id){
        Predstavka predstavka = predstavkaService.findOne(id);
        Sluzbenik sluzbenik = SluzbenikMapper.mapModel(sluzbenikService.getOne(1L));
        Izvestaj novIzvestaj = new Izvestaj();
        novIzvestaj.setIzvestaj("Vaša predstavka je odbijena.");
        novIzvestaj.setPrihvaceno(false);
        novIzvestaj.setVremePodnosenja(new Date());
        novIzvestaj.setSluzbenik(sluzbenik);
        Izvestaj vracen = izvestajService.save(novIzvestaj);
        predstavka.setIzvestaj(vracen);
        predstavkaService.save(predstavka);
        return ResponseEntity.ok().build();
    }
}
