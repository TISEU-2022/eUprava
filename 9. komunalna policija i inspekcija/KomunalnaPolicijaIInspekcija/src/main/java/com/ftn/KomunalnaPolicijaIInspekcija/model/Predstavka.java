package com.ftn.KomunalnaPolicijaIInspekcija.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
@Builder
public class Predstavka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naslov", nullable = false)
    private String naslov;

    @Column(name = "opis", nullable = false)
    private String opis;

    @Column(name = "vremePodnosenja", nullable = false)
    private Date vremePodnosenja;

    @Column(name = "adresaDogadjaja", nullable = false)
    private String adresaDogadjaja;

    @Column(name = "mestoDogadjaja", nullable = false)
    private String mestoDogadjaja;

    @Column(name = "datumDogadjaja", nullable = true)
    private Date datumDogadjaja;

    @ElementCollection
    @CollectionTable(name = "predstavka_datoteke", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "datoteka", columnDefinition = "MEDIUMBLOB")
    private List<String> datoteke;

    @ManyToOne
    private VrstaPredstavke vrstaPredstavke;

    @OneToOne
    private Izvestaj izvestaj;

}
