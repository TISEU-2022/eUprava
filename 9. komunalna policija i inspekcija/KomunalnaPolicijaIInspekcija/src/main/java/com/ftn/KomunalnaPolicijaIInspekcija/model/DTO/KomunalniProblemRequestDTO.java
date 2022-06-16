package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaKomunalnogProblema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class KomunalniProblemRequestDTO {

    private String ime;
    private String prezime;
    private String jmbg;
    private String adresa;
    private String mesto;
    private String email;
    private String telefon;
    private int pttBroj;

    private String opis;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumDogadjaja;
    private Long vrstaKomunalnogProblemaId;
    private List<String> datoteke;

}
