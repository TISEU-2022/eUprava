package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.IzvestajDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.service.IzvestajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin
@RequestMapping(value = "api/izvestaj")
public class IzvestajController {

    @Autowired
    private IzvestajService izvestajService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<IzvestajDTO> getOneIzvestaj(@PathVariable("id") Long id){
        try {
            IzvestajDTO izvestajDTO = izvestajService.getOne(id);
            return new ResponseEntity<>(izvestajDTO, HttpStatus.OK);
        }catch (NotFoundException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<IzvestajDTO> updateIzvestaj(@PathVariable("id") Long id, @RequestBody IzvestajDTO izvestajDTO){
        try {
            izvestajService.updateIzvestaj(id, izvestajDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Long> createIzvestaj(@RequestBody IzvestajDTO izvestajDTO){
        Long id = izvestajService.createIzvestaj(izvestajDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteIzvestaj(@PathVariable("id") Long id){
        boolean deleted = izvestajService.deleteIzvestaj(id);
        if (deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
