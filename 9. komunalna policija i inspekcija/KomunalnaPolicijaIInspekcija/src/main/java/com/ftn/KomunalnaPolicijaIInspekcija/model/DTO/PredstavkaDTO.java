package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

import com.ftn.KomunalnaPolicijaIInspekcija.model.Izvestaj;
import com.ftn.KomunalnaPolicijaIInspekcija.model.VrstaPredstavke;
import lombok.*;

import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PredstavkaDTO {

    private Long id;
    private String naslov;
    private String opis;
    private Date vremePodnosenja;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    private Date datumDogadjaja;
    private List<String> putanjeDoDatoteka;
    private VrstaPredstavkeDTO vrstaPredstavkeDTO;
    private IzvestajDTO izvestajDTO;
}
