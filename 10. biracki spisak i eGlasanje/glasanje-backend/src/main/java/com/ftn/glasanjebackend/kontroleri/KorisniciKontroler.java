package com.ftn.glasanjebackend.kontroleri;


import com.ftn.glasanjebackend.model.Korisnik;
import com.ftn.glasanjebackend.model.dto.KorisnikDTO;
import com.ftn.glasanjebackend.model.dto.KorisnikPrijavaDTO;
import com.ftn.glasanjebackend.service.KorisniciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/korisnici")
@CrossOrigin
public class KorisniciKontroler {

    @Autowired
    private KorisniciService korisniciService;


    @PostMapping(value = "/prijava")
    public ResponseEntity<KorisnikDTO> prijava(String jmbg, String lozinka){
        Korisnik korisnik = korisniciService.findKorisnikByJmbgAndLozinka(jmbg, lozinka);

        if(korisnik != null){
            KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);

            return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
      }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
