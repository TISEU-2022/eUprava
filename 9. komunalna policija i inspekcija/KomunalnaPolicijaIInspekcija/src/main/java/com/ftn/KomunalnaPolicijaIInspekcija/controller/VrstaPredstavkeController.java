package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.VrstaPredstavkeDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaPredstavke;
import com.ftn.KomunalnaPolicijaIInspekcija.service.VrstaPredstavkeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/vrsta-predstavke")
public class VrstaPredstavkeController {

    @Autowired
    private VrstaPredstavkeService vrstaPredstavkeService;

    @GetMapping
    public ResponseEntity<List<VrstaPredstavkeDTO>> findAll(){
        return new ResponseEntity<>(vrstaPredstavkeService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VrstaPredstavkeDTO> findOne(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(vrstaPredstavkeService.getOne(id), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VrstaPredstavkeDTO> updateVrstuPredstavke(@PathVariable("id") Long id, @RequestBody VrstaPredstavkeDTO vrstaPredstavkeDTO){
        try {
            vrstaPredstavkeService.update(id, vrstaPredstavkeDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Long> createVrstuPredstavke(@RequestBody VrstaPredstavkeDTO vrstaPredstavkeDTO){
        Long id = vrstaPredstavkeService.save(vrstaPredstavkeDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteVrstaPredstavke(@PathVariable("id") Long id){
        boolean deleted = vrstaPredstavkeService.delete(id);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
