package com.ftn.glasanjebackend.kontroleri;

import com.ftn.glasanjebackend.model.Glas;
import com.ftn.glasanjebackend.model.Korisnik;
import com.ftn.glasanjebackend.model.dto.GlasDTO;
import com.ftn.glasanjebackend.service.GlasService;
import com.ftn.glasanjebackend.service.IzboriService;
import com.ftn.glasanjebackend.service.KandidatiService;
import com.ftn.glasanjebackend.service.KorisniciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/glasanje")
@CrossOrigin
public class GlasanjeKontroler {

    @Autowired
    GlasService glasService;
    @Autowired
    KorisniciService korisniciService;
    @Autowired
    KandidatiService kandidatiService;
    @Autowired
    IzboriService izboriService;

    @PostMapping
    public ResponseEntity<GlasDTO> save(@RequestBody GlasDTO glasDTO){
        Glas glas = new Glas(glasDTO.getId(), izboriService.findOne(glasDTO.getIzbori()), kandidatiService.findOne(glasDTO.getKandidat()), korisniciService.findById(glasDTO.getKorisnik()));
        glasService.save(glas);
        return new ResponseEntity<>(glasDTO, HttpStatus.OK);
    }
}
