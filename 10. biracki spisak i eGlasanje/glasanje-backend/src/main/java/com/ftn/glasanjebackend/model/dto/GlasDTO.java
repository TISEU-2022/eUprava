package com.ftn.glasanjebackend.model.dto;

import com.ftn.glasanjebackend.model.Glas;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GlasDTO {
    private Long id;
    private Long izbori;
    private Long kandidat;
    private Long korisnik;

    public GlasDTO(Glas glas){
        this.id = glas.getId();
        this.izbori = glas.getIzbori().getId();
        this.kandidat = glas.getKandidat().getId();
        this.korisnik = glas.getKorisnik().getId();
    }
}
