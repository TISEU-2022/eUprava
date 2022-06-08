package com.ftn.glasanjebackend.model.dto;

import com.ftn.glasanjebackend.model.Korisnik;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class KorisnikDTO {

    private Long id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String opstina;
    private Boolean sluzbenik;
    private String jmbg;
    private String lozinka;

    public KorisnikDTO(Korisnik korisnik){
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.datumRodjenja = korisnik.getDatumRodjenja();
        this.opstina = korisnik.getOpstina().toString();
        this.sluzbenik = korisnik.getSluzbenik();
        this.jmbg = korisnik.getJmbg();
        this.lozinka = korisnik.getLozinka();
    }
}
