package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.SluzbenikDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("api/sluzbenik")
public class SluzbenikController {

    @Autowired
    private SluzbenikService sluzbenikService;

    @GetMapping( value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SluzbenikDTO> getOneSluzbenik(@PathVariable(value = "id") Long id){
        try {
            SluzbenikDTO sluzbenikDTO = sluzbenikService.getOne(id);
            return new ResponseEntity<SluzbenikDTO>(sluzbenikDTO, HttpStatus.OK);
        }catch (NotFoundException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SluzbenikDTO> updateSluzbenik(@PathVariable("id") Long id, @RequestBody SluzbenikDTO sluzbenikDTO){
        try {
            sluzbenikService.updateSluzbenik(id, sluzbenikDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Long> createSluzbenik(@RequestBody SluzbenikDTO sluzbenikDTO){
        Long sluzbenikId = sluzbenikService.createSluzbenik(sluzbenikDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sluzbenikId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteSluzbenik(@PathVariable(value = "id") Long id){
        boolean deleted = sluzbenikService.deleteSluzbenik(id);
        if (deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
