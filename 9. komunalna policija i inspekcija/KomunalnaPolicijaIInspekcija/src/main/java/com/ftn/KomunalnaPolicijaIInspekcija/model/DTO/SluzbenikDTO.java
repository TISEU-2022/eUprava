package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class SluzbenikDTO {

    private UUID id;
    private String ime;
    private String prezime;
    private String email;
    private String jmbg;
}
