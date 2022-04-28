package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class SluzbenikDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String jmbg;
}
