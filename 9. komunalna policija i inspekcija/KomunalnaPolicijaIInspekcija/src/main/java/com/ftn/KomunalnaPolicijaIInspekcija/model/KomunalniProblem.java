package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "komunalniProblem")
public class KomunalniProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String opis;

    @ManyToOne
    private Podnosilac podnosilac;
    private String adresaDogadjaja;
    private String mestoDogadjaja;
    private Date datumPodnosenja;
    private Date datumDogadjaja;

    @ManyToOne
    private VrstaKomunalnogProblema vrstaKomunalnogProblema;

    @ElementCollection
    @CollectionTable(name = "komunalni_problem_datoteke", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "datoteka", columnDefinition = "MEDIUMBLOB")
    private List<String> datoteke;

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
                ", datoteke=" + datoteke +
                ", izvestaj=" + izvestaj +
                '}';
    }
}
