package com.ftn.glasanjebackend.kontroleri;

import com.ftn.glasanjebackend.model.Izbori;
import com.ftn.glasanjebackend.model.Kandidat;
import com.ftn.glasanjebackend.model.dto.IzboriDTO;
import com.ftn.glasanjebackend.model.dto.KandidatDTO;
import com.ftn.glasanjebackend.service.IzboriService;
import com.ftn.glasanjebackend.service.KandidatiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/kandidati")
@CrossOrigin
public class KandidatiKontroler {

    @Autowired
    private KandidatiService kandidatiService;


    @GetMapping(value = "/kandidati")
    public ResponseEntity<List<KandidatDTO>> getAllKandidati(){
        List<Kandidat> kandidati = kandidatiService.findAll();

        List<KandidatDTO> kandidatDTO = new ArrayList<>();
        for(Kandidat k : kandidati){
            kandidatDTO.add(new KandidatDTO(k));
        }
        return new ResponseEntity<>(kandidatDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<KandidatDTO> getKandidat(@PathVariable Long id) {
        Kandidat kandidat = kandidatiService.findOne(id);
        if(kandidat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new KandidatDTO(kandidat),HttpStatus.OK);
    }
}
