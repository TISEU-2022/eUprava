package com.ftn.glasanjebackend.model.dto;

import com.ftn.glasanjebackend.model.Izbori;
import com.ftn.glasanjebackend.model.Korisnik;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<IzboriDTO> izbori;

    public KorisnikDTO(Korisnik korisnik){
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.datumRodjenja = korisnik.getDatumRodjenja();
        this.opstina = korisnik.getOpstina().toString();
        this.sluzbenik = korisnik.getSluzbenik();
        this.jmbg = korisnik.getJmbg();
        this.lozinka = korisnik.getLozinka();

        this.izbori = new ArrayList<IzboriDTO>();
        for (Izbori izbori: korisnik.getIzbori()) {
            this.izbori.add(new IzboriDTO(izbori));
        }
    }
}
