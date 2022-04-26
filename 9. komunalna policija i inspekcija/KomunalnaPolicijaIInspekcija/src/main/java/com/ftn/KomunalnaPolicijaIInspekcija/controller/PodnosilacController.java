package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.KomunalniProblemDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.PodnosilacDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.KomunalniProblemMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.PodnosilacMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PodnosilacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(value = "api/podnosilac")
public class PodnosilacController {

    @Autowired
    private PodnosilacService podnosilacService;

    @GetMapping
    public ResponseEntity<List<PodnosilacDTO>> getAll(){
        List<Podnosilac> podnosioci = podnosilacService.getAll();
        List<PodnosilacDTO> dtos = new ArrayList<>();
        for(Podnosilac podnosilac : podnosioci){
            dtos.add(PodnosilacMapper.mapDTO(podnosilac));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PodnosilacDTO> getOne(@PathVariable UUID id){
        Podnosilac podnosilac = podnosilacService.getOne(id);

        return new ResponseEntity<>(PodnosilacMapper.mapDTO(podnosilac), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> createKomunalniProblem(@RequestBody PodnosilacDTO podnosilacDTO){
        UUID id = podnosilacService.createPodnosilac(PodnosilacMapper.mapModel(podnosilacDTO));

        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location).body(id);
    }
}
