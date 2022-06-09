package com.ftn.glasanjebackend.model.dto;

import com.ftn.glasanjebackend.model.Korisnik;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KorisnikLoginDTO {

    private String jmbg;
    private String lozinka;

    public KorisnikLoginDTO(Korisnik korisnik){
        this.jmbg = korisnik.getJmbg();
        this.lozinka = korisnik.getLozinka();
    }
}
