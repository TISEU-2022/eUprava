package com.ftn.glasanjebackend.model.dto;

import com.ftn.glasanjebackend.model.Izbori;
import com.ftn.glasanjebackend.model.Kandidat;
import com.ftn.glasanjebackend.model.enumeration.EOpstina;
import com.ftn.glasanjebackend.model.enumeration.ETipIzbora;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class IzboriDTO {

    private long id;
    private String naziv;
    private Date datum;
    private List<KandidatDTO> kandidatiDTO;
    private String eTipIzbora;
    private String eOpstina;

    public IzboriDTO(Izbori izbori){
        this.id = izbori.getId();
        this.naziv = izbori.getNaziv();
        this.datum = izbori.getDatum();
        this.kandidatiDTO = new ArrayList<KandidatDTO>();
        for (Kandidat kandidat: izbori.getKandidati()) {
            kandidatiDTO.add(new KandidatDTO(kandidat));
        }
        this.eTipIzbora = izbori.getTipIzbora().toString();
        if (this.eTipIzbora.equals(ETipIzbora.OPSTINSKI.toString())){
            this.eOpstina = izbori.getEOpstina().toString();
        }else{
            this.eOpstina = null;
        }
    }



}
