package com.ftn.glasanjebackend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RezultatiDTO {

    private String kandidat;
    private int br_glasova;

    public RezultatiDTO(String kandidat, int br_glasova) {
        this.kandidat = kandidat;
        this.br_glasova = br_glasova;
    }
}
