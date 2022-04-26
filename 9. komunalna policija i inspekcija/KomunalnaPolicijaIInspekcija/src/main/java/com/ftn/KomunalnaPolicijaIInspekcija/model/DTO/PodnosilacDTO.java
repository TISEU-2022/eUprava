package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class PodnosilacDTO {

    private UUID id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String adresa;
    private String mesto;
    private String email;
    private String telefon;
    private int pttBroj;

}
