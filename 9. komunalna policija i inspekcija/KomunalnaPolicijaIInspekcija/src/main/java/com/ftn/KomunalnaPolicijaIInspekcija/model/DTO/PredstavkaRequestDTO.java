package com.ftn.KomunalnaPolicijaIInspekcija.model.DTO;

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
public class PredstavkaRequestDTO {

    private String naslov;
    private String opis;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumDogadjaja;
    private List<String> datoteke;
    private Long vrstaPredstavkeId;
}
