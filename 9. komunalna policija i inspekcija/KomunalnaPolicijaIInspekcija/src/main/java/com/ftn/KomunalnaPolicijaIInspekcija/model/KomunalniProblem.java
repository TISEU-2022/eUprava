package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class KomunalniProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String opis;
    private Podnosilac podnosilac;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    private Date datumPodnosenja;
    private Date datumDogadjaja;
    private VrstaKomunalnogProblema vrstaKomunalnogProblema;
    private List<String> putanjeDoDatoteka;

    @OneToOne
    private Izvestaj izvestaj;

    @Override
    public String toString() {
        return "KomunalniProblem{" +
                "id=" + id +
                ", opis='" + opis + '\'' +
                ", podnosilac=" + podnosilac +
                ", adresaDogadjaja='" + adresaDogadjaja + '\'' +
                ", mestoDogadjaja='" + mestoDogadjaja + '\'' +
                ", datumPodnosenja=" + datumPodnosenja +
                ", datumDogadjaja=" + datumDogadjaja +
                ", vrstaKomunalnogProblema=" + vrstaKomunalnogProblema +
                ", putanjeDoDatoteka=" + putanjeDoDatoteka +
                '}';
    }
}
