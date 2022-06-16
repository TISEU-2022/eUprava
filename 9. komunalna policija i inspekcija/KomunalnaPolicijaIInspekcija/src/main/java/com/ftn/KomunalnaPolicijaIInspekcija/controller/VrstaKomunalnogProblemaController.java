package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaKomunalnogProblema;
import com.ftn.KomunalnaPolicijaIInspekcija.service.VrstaKomunalnogProblemaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/vrsta-komunalnog-problema")
public class VrstaKomunalnogProblemaController {

    private final VrstaKomunalnogProblemaService vrstaKomunalnogProblemaService;

    public VrstaKomunalnogProblemaController(VrstaKomunalnogProblemaService vrstaKomunalnogProblemaService) {
        this.vrstaKomunalnogProblemaService = vrstaKomunalnogProblemaService;
    }

    @GetMapping
    public ResponseEntity<List<VrstaKomunalnogProblema>> findAll() {
        return new ResponseEntity<>(vrstaKomunalnogProblemaService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VrstaKomunalnogProblema> findOne(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vrstaKomunalnogProblemaService.getOne(id), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping
    public ResponseEntity<Long> createVrstaKomunalnogProblema(@RequestBody VrstaKomunalnogProblema vrstaKomunalnogProblema){
        Long id = vrstaKomunalnogProblemaService.save(vrstaKomunalnogProblema);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VrstaKomunalnogProblema> updateVrstaKomunalnogProblema(@PathVariable long id, @RequestBody VrstaKomunalnogProblema vrstaKomunalnogProblema){
        try {
            vrstaKomunalnogProblemaService.update(id, vrstaKomunalnogProblema);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteVrstaKomunalnogProblema(@PathVariable("id") Long id){
        boolean deleted = vrstaKomunalnogProblemaService.delete(id);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}