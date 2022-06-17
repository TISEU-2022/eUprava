package com.ftn.glasanjebackend.kontroleri;

import com.ftn.glasanjebackend.model.Glas;
import com.ftn.glasanjebackend.model.Izbori;
import com.ftn.glasanjebackend.model.Korisnik;
import com.ftn.glasanjebackend.model.dto.GlasDTO;
import com.ftn.glasanjebackend.service.GlasService;
import com.ftn.glasanjebackend.service.IzboriService;
import com.ftn.glasanjebackend.service.KandidatiService;
import com.ftn.glasanjebackend.service.KorisniciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

//    @PreAuthorize("hasAnyRole('KORISNIK','SLUZBENIK')")
    @PostMapping
    public ResponseEntity<GlasDTO> save(@RequestBody GlasDTO glasDTO){
        boolean glasao = false;
        Korisnik korisnik = korisniciService.findById(glasDTO.getKorisnik());
        for (Izbori izbori: korisnik.getIzbori()) {
            if (glasDTO.getIzbori() == izbori.getId()) {
                glasao = true;
                break;
            }
        }
        if (glasao){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            Glas glas = new Glas(glasDTO.getId(), izboriService.findOne(glasDTO.getIzbori()), kandidatiService.findOne(glasDTO.getKandidat()));
            korisnik.getIzbori().add(izboriService.findOne(glasDTO.getIzbori()));
            korisniciService.save(korisnik);
            glasService.save(glas);
            return new ResponseEntity<>(glasDTO, HttpStatus.OK);
        }
    }
}
