package com.ftn.glasanjebackend.model;

import com.ftn.glasanjebackend.model.enumeration.EOpstina;
import com.ftn.glasanjebackend.model.enumeration.EUloga;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private EOpstina opstina;
    private String jmbg;
    private String lozinka;
    @OneToMany
    private List<Izbori> izbori;
    private EUloga uloga;
}
