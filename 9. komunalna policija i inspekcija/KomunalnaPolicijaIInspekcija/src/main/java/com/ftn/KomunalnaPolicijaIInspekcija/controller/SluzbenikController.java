package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.SluzbenikDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.SluzbenikMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Sluzbenik;
import com.ftn.KomunalnaPolicijaIInspekcija.service.SluzbenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("api/sluzbenik")
public class SluzbenikController {

    @Autowired
    private SluzbenikService sluzbenikService;

    @GetMapping( value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SluzbenikDTO> getOneSluzbenik(@PathVariable(value = "id") UUID uuid){
        try {
            SluzbenikDTO sluzbenikDTO = sluzbenikService.getOne(uuid);
            return new ResponseEntity<SluzbenikDTO>(sluzbenikDTO, HttpStatus.OK);
        }catch (NotFoundException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SluzbenikDTO> updateSluzbenik(@PathVariable("id") UUID uuid, @RequestBody SluzbenikDTO sluzbenikDTO){
        try {
            sluzbenikService.updateSluzbenik(uuid, sluzbenikDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<UUID> createSluzbenik(@RequestBody SluzbenikDTO sluzbenikDTO){
        UUID sluzbenikUuid = sluzbenikService.createSluzbenik(sluzbenikDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sluzbenikUuid).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteSluzbenik(@PathVariable(value = "id") UUID uuid){
        boolean deleted = sluzbenikService.deleteSluzbenik(uuid);
        if (deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
