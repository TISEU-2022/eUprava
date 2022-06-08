package com.ftn.glasanjebackend.model;

import com.ftn.glasanjebackend.model.enumeration.EOpstina;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private EOpstina opstina;
    private Boolean sluzbenik;
}
