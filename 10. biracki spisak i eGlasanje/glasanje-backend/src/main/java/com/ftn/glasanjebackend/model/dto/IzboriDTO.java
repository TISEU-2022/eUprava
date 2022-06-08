package com.ftn.glasanjebackend.model.dto;

import com.ftn.glasanjebackend.model.Izbori;
import com.ftn.glasanjebackend.model.Kandidat;
import com.ftn.glasanjebackend.model.enumeration.EOpstina;
import com.ftn.glasanjebackend.model.enumeration.ETipIzbora;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class IzboriDTO {

    private long id;
    private String naziv;
    private Date datum;
    private KandidatDTO kandidatDTO;
    private String eTipIzbora;
    private String eOpstina;

    public IzboriDTO(Izbori izbori){
        this.id = izbori.getId();
        this.naziv = izbori.getNaziv();
        this.datum = izbori.getDatum();
        this.kandidatDTO = new KandidatDTO((Kandidat) izbori.getKandidati());
        this.eTipIzbora = izbori.getTipIzbora().toString();
        this.eOpstina = izbori.getEOpstina().toString();
    }



}
