package com.ftn.glasanjebackend.model.dto;

import com.ftn.glasanjebackend.model.Kandidat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KandidatDTO {

    private long id;
    private String imeStranke;
    private String imePredstavnika;
    private String slogan;

    public KandidatDTO(Kandidat kandidat){
        this.id = kandidat.getId();
        this.imeStranke = kandidat.getImeStranke();
        this.imePredstavnika = kandidat.getImePredstavnika();
        this.slogan = kandidat.getSlogan();
    }
}
