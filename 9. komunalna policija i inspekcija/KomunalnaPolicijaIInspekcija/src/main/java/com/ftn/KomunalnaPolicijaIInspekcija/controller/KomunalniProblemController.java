package com.ftn.KomunalnaPolicijaIInspekcija.controller;

import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.KomunalniProblemDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.KomunalniProblemRequestDTO;
import com.ftn.KomunalnaPolicijaIInspekcija.model.DTO.mapper.KomunalniProblemMapper;
import com.ftn.KomunalnaPolicijaIInspekcija.model.KomunalniProblem;
import com.ftn.KomunalnaPolicijaIInspekcija.service.KomunalniProblemService;
import com.ftn.KomunalnaPolicijaIInspekcija.service.PodnosilacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/komunalni-problem")
public class KomunalniProblemController {

    @Autowired
    private KomunalniProblemService komunalniProblemService;

    @Autowired
    private PodnosilacService podnosilacService;

    @GetMapping
    public ResponseEntity<List<KomunalniProblemDTO>> getAll(){
        List<KomunalniProblem> komunalniProblemi = komunalniProblemService.getAll();
        List<KomunalniProblemDTO> dtos = new ArrayList<>();
        for(KomunalniProblem kp : komunalniProblemi){
            dtos.add(KomunalniProblemMapper.mapDTO(kp));
        }
        System.out.println(dtos);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KomunalniProblemDTO> getOne(@PathVariable Long id){
        KomunalniProblem komunalniProblem = komunalniProblemService.getOne(id);

        return new ResponseEntity<>(KomunalniProblemMapper.mapDTO(komunalniProblem), HttpStatus.OK);
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<Long> createKomunalniProblem(@ModelAttribute KomunalniProblemRequestDTO komunalniProblemRequestDTO){

        KomunalniProblem komunalniProblem = KomunalniProblemMapper.mapModel(komunalniProblemRequestDTO);
        komunalniProblem.setDatumPodnosenja(new Date());

        podnosilacService.create(komunalniProblem.getPodnosilac());
        Long id = komunalniProblemService.createKomunalniProblem(komunalniProblem);

        String location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();

        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location).body(id);
    }

}
