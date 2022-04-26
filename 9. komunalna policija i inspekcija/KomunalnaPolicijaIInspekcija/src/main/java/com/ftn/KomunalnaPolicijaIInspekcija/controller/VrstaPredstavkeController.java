package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.exception.NotFoundException;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.VrstaPredstavkeDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.service.VrstaPredstavkeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<VrstaPredstavkeDTO> find(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(vrstaPredstavkeService.getOne(id), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
