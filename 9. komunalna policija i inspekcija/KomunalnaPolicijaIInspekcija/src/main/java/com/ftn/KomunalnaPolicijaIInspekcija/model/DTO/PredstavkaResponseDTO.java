package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PredstavkaResponseDTO {

    private Long id;
    private String naslov;
    private String opis;
    private Date vremePodnosenja;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    private Date datumDogadjaja;
    private List<String> datoteke;
    private VrstaPredstavkeDTO vrstaPredstavke;
    private IzvestajDTO izvestaj;
    private PodnosilacDTO podnosilac;
}
