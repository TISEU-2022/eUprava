package com.ftn.glasanjebackend.kontroleri;

import com.ftn.glasanjebackend.model.Izbori;
import com.ftn.glasanjebackend.model.dto.IzboriDTO;
import com.ftn.glasanjebackend.model.dto.KorisnikDTO;
import com.ftn.glasanjebackend.model.enumeration.EOpstina;
import com.ftn.glasanjebackend.model.enumeration.ETipIzbora;
import com.ftn.glasanjebackend.service.IzboriService;
import com.ftn.glasanjebackend.service.KorisniciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/izbori")
@CrossOrigin
public class IzboriKontroler {

    @Autowired
    private IzboriService izboriService;
    @Autowired
    private KorisniciService korisniciService;


    @PostMapping
    public ResponseEntity<List<IzboriDTO>> getAllIzbori(@RequestBody Long id){
        List<Izbori> izbori = izboriService.findAll();

        List<IzboriDTO> izboriDTO = new ArrayList<>();
        Date datum = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Izbori i : izbori){
            if(i.getTipIzbora().equals(ETipIzbora.REPUBLICKI)) {
                if (sdf.format(i.getDatum()).equals(sdf.format(datum))) {
                    izboriDTO.add(new IzboriDTO(i));
                }
            }
            else{
                KorisnikDTO prijavljeniKorisnik = new KorisnikDTO(korisniciService.findById(id));
                if(prijavljeniKorisnik.getOpstina().equals(i.getEOpstina().toString())) {
                    if (sdf.format(i.getDatum()).equals(sdf.format(datum))) {
                        izboriDTO.add(new IzboriDTO(i));
                    }
                }
            }
        }
        return new ResponseEntity<>(izboriDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<IzboriDTO> getIzbor(@PathVariable Long id) {
        Izbori izbori = izboriService.findOne(id);
        if(izbori == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new IzboriDTO(izbori),HttpStatus.OK);
    }



}
