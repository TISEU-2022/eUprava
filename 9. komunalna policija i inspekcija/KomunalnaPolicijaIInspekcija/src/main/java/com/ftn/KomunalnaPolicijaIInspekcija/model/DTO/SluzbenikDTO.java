package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@Builder
public class SluzbenikDTO {

    private UUID id;
    private String ime;
    private String prezime;
    private String email;
    private String jmbg;
}
