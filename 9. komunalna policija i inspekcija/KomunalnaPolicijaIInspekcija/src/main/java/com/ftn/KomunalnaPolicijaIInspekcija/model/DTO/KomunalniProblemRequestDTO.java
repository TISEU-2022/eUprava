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

    private String opis;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumDogadjaja;
    private Long vrstaKomunalnogProblemaId;
    private List<String> datoteke;

}
