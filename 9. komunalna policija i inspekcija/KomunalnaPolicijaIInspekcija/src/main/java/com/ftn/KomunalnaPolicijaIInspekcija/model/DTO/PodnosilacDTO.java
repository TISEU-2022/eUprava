package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
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