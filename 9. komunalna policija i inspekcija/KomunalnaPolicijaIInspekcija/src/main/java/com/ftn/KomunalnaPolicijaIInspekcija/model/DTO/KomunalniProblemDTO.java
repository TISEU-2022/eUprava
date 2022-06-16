package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Podnosilac;
import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaKomunalnogProblema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class KomunalniProblemDTO {

    private Long id;
    private String opis;
    private PodnosilacDTO podnosilacDTO;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    private Date datumPodnosenja;
    private Date datumDogadjaja;
    private IzvestajDTO izvestajDTO;
    private VrstaKomunalnogProblema vrstaKomunalnogProblema;
    private List<String> datoteke;
    private PodnosilacDTO podnosilac;
    private IzvestajDTO izvestaj;
}
