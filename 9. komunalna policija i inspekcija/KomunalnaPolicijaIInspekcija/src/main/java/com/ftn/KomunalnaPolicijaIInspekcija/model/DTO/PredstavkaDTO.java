package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PredstavkaDTO {

    private Long id;
    private String naslov;
    private String opis;
    private Date vremePodnosenja;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    private Date datumDogadjaja;
    private List<String> putanjeDoDatoteka;
}
